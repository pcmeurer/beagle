/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.zup.beagle.sample.widgets

import android.content.Context
import android.view.View
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.android.widget.form.InputWidget

//@RegisterWidget
data class SampleTextField(val placeholder: String = "") : InputWidget() {

    @Transient
    private lateinit var textFieldView: EditText

    override fun getValue() = textFieldView.text.toString()

    override fun onErrorMessage(message: String) {
        textFieldView.error = message
    }

    override fun buildView(context: Context) = EditText(context).apply {
        textFieldView = this

        textFieldView.isSingleLine = true
        doOnTextChanged { _, _, _, _ -> notifyChanges() }
        textFieldView.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) onFocus() else onBlur()
        }
    }

    override fun onBind(widget: Widget, view: View) {
        (widget as? SampleTextField)?.let {
            textFieldView.hint = it.placeholder
        }
    }
}
