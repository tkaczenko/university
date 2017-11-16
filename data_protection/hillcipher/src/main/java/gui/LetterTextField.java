package io.github.tkaczenko.HillCipher.src.main.java.gui;

import javafx.scene.control.TextField;

/**
 * Created by tkaczenko on 07.10.16.
 */
public class LetterTextField extends TextField {
    @Override
    public void replaceText(int start, int end, String text) {
        if (text.matches("[^а-щА-ЩЬьЮюЯяЇїІіЄєҐґ,.;?_-~ !1234567890:\"]") || text.isEmpty()) {
            super.replaceText(start, end, text);
        }
    }

    @Override
    public void replaceSelection(String replacement) {
        super.replaceSelection(replacement);
    }
}
