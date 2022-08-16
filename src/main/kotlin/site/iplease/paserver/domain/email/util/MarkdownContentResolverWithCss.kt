package site.iplease.paserver.domain.email.util

import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.springframework.core.io.ClassPathResource
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono
import site.iplease.paserver.domain.email.config.EmailProperties
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

@Component
class MarkdownContentResolverWithCss(
    private val emailProperties: EmailProperties
): EmailContentResolver {

    fun cssPrefix() =
        ClassPathResource(emailProperties.cssFilePath)
            .let { resource -> Paths.get(resource.uri) }
            .let{ path -> Files.readString(path) }
            .let{ css -> "<body><style>$css</style>".trimIndent().replace("\n", "") }

    override fun resolve(content: String): Mono<String> =
        CommonMarkFlavourDescriptor().toMono()
            .map { flavour -> flavour to MarkdownParser(flavour).buildMarkdownTreeFromString(content) }
            .map { HtmlGenerator(markdownText = content, root = it.second, flavour = it.first).generateHtml() }
            .map { it.replace("<body>", cssPrefix()) }
            .map {Jsoup.parse(it) }
            .map { doc -> toInline(doc) }

    private fun toInline(doc: Document): String {
        val els: Elements = doc.select("style")
        for (e in els) {
            val styleRules: String = e.allElements[0].data().replace("\n", "").trim()
            val st = StringTokenizer(styleRules, "{}")
            while (st.countTokens() > 1) {
                val selector: String = st.nextToken()
                val properties: String = st.nextToken()
                val selectedElements: Elements = doc.select(selector)
                for (selElem in selectedElements) {
                    val oldProperties: String = selElem.attr("style")
                    selElem.attr("style", if (oldProperties.isNotEmpty()) concatenateProperties(oldProperties, properties) else properties) }
            }; e.remove() }
        return doc.toString()
    }

    private fun concatenateProperties(oldProp: String, newProp: String): String =
        newProp.let { if (!newProp.endsWith(";")) return@let "$it;" else it}.let { "$it${oldProp.trim()}" }
}