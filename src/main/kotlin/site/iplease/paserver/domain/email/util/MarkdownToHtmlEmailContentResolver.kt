package site.iplease.paserver.domain.email.util

import org.intellij.markdown.flavours.commonmark.CommonMarkFlavourDescriptor
import org.intellij.markdown.html.HtmlGenerator
import org.intellij.markdown.parser.MarkdownParser
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Component
class MarkdownToHtmlEmailContentResolver: EmailContentResolver {
    override fun resolve(content: String): Mono<String> =
        CommonMarkFlavourDescriptor().toMono()
            .map { flavour -> flavour to MarkdownParser(flavour).buildMarkdownTreeFromString(content) }
            .map { HtmlGenerator(markdownText = content, root = it.second, flavour = it.first).generateHtml() }
}