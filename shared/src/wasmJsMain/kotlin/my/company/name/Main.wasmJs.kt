package my.company.name

import kotlinx.browser.window

actual fun notify(message: String) {
    window.alert(message)
}