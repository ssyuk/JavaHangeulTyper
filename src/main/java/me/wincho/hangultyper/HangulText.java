package me.wincho.hangultyper;

import java.util.ArrayList;
import java.util.List;

public class HangulText {
    private final List<HangulChar> chars = new ArrayList<>();

    public HangulText(String str) {
        for (char c : str.toCharArray())
            chars.add(HangulChar.makeHangulCharFromCharacter(c));
    }


    public void append(char c) {
        if (c == '\b') {
            if (chars.size() != 0)
                chars.remove(chars.size() - 1);
            return;
        }
        if (c == '\n') chars.add(HangulChar.makeHangulCharFromCharacter('\n'));
        HangulChar hChar = null;
        if (chars.size() != 0) hChar = chars.get(chars.size() - 1);
        if (HangulChar.isConsonants(c)) {
            if (hChar != null && hChar.canAppendJongSeong()) {
                hChar.appendJongSeong(c);
                chars.set(chars.size() - 1, hChar);
            } else {
                chars.add(HangulChar.makeHangulCharFromCharacter(c));
            }
        } else if (HangulChar.isVowels(c)) {
            if (hChar != null && hChar.canAppendJungSeong(c)) {
                hChar.appendJungSeong(c);
                chars.set(chars.size() - 1, hChar);
            } else if (hChar != null && hChar.hasJongSeong()) {
                int jong = hChar.getJongSeongAndRemove();

                HangulChar newChar = HangulChar.makeHangulCharFromCharacter(' ');
                newChar.setChoSeong(jong);
                newChar.appendJungSeong(c);
                chars.add(newChar);
            } else {
                chars.add(HangulChar.makeHangulCharFromCharacter(c));
            }
        } else {
            chars.add(HangulChar.makeHangulCharFromCharacter(c));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (HangulChar aChar : chars) {
            builder.append(aChar.buildChar());
        }
        return builder.toString();
    }
}
