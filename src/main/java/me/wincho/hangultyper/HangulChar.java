package me.wincho.hangultyper;

import java.util.List;

public class HangulChar {
    public final static List<Character> CHO_SEONG = List.of('ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ');
    public final static List<Character> JUNG_SEONG = List.of('ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ', 'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ', 'ㅡ', 'ㅢ', 'ㅣ');
    public final static List<Character> JONG_SEONG = List.of(' ', 'ㄱ', 'ㄲ', 'ㄳ', 'ㄴ', 'ㄵ', 'ㄶ', 'ㄷ', 'ㄹ', 'ㄺ', 'ㄻ', 'ㄼ', 'ㄽ', 'ㄾ', 'ㄿ', 'ㅀ', 'ㅁ', 'ㅂ', 'ㅄ', 'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ');

    private Integer cho_seong = null;
    private Integer jung_seong = null;
    private Integer jong_seong = null;
    private Character character = null;

    private HangulChar() {
    }

    public static HangulChar makeHangulCharFromCharacter(char character) {
        HangulChar hangulChar = new HangulChar();
        if (character >= 0xAC00 && character <= 0xB00F) {
            char uniVal = (char) (character - 0xAC00);
            int cho = (char) (uniVal / 28 / 21);
            int jung = (char) ((uniVal) / 28 % 21);
            int jong = (char) (uniVal % 28);
            hangulChar.cho_seong = (int) CHO_SEONG.get(cho);
            hangulChar.jung_seong = (int) JUNG_SEONG.get(jung);
            if (jong == 0) hangulChar.jong_seong = null;
            else hangulChar.jong_seong = (int) JONG_SEONG.get(jong);
        } else if (isConsonants(character)) {
            hangulChar.cho_seong = (int) character;
        } else {
            hangulChar.character = character;
        }
        return hangulChar;
    }

    public static boolean isVowels(char c) {
        int start = 'ㅏ';
        int end = 'ㅣ';
        return c >= start && c <= end;
    }

    public static boolean isConsonants(char c) {
        int start = 'ㄱ';
        int end = 'ㅎ';
        int sStart = 'ㄲ';
        int sEnd = 'ㅄ';
        return c >= sStart && c <= sEnd || c >= start && c <= end;
    }

    public boolean canAppendJungSeong(int jungSeong) {
        if (jung_seong != null) {
            if (jung_seong == 'ㅗ') {
                if (jungSeong == 'ㅏ') {
                    return true;
                } else if (jungSeong == 'ㅐ') {
                    return true;
                } else if (jungSeong == 'ㅣ') {
                    return true;
                }
            }
            if (jung_seong == 'ㅜ') {
                if (jungSeong == 'ㅓ') {
                    return true;
                } else if (jungSeong == 'ㅔ') {
                    return true;
                } else if (jungSeong == 'ㅣ') {
                    return true;
                }
            }
        }
        return cho_seong != null && jong_seong == null;
    }

    public void appendJungSeong(int jungSeong) {
        if (canAppendJungSeong(jungSeong)) {
            if (jung_seong != null && jung_seong == 'ㅗ') {
                if (jungSeong == 'ㅏ') {
                    this.jung_seong = (int) 'ㅘ';
                } else if (jungSeong == 'ㅐ') {
                    this.jung_seong = (int) 'ㅙ';
                } else if (jungSeong == 'ㅣ') {
                    this.jung_seong = (int) 'ㅟ';
                }
            } else if (jung_seong != null && jung_seong == 'ㅜ') {
                if (jungSeong == 'ㅓ') {
                    this.jung_seong = (int) 'ㅝ';
                } else if (jungSeong == 'ㅔ') {
                    this.jung_seong = (int) 'ㅞ';
                } else if (jungSeong == 'ㅣ') {
                    this.jung_seong = (int) 'ㅟ';
                }
            } else {
                this.jung_seong = jungSeong;
            }
        }
    }

    public boolean canAppendJongSeong() {
        return cho_seong != null && jung_seong != null && jong_seong == null;
    }

    public void appendJongSeong(int jong_seong) {
        if (canAppendJongSeong())
            this.jong_seong = jong_seong;
    }

    public int getJongSeongAndRemove() {
        int jong = jong_seong;
        jong_seong = null;
        return jong;
    }

    @SuppressWarnings({"ConstantConditions", "PointlessArithmeticExpression"})
    public char buildChar() {
        if (cho_seong != null && jung_seong == null && jong_seong == null) {
            return (char) (cho_seong + 0);
        } else if (cho_seong != null && jung_seong != null && jong_seong == null) {
            return (char) ((CHO_SEONG.indexOf((char) (cho_seong + 0)) * 21 + JUNG_SEONG.indexOf((char) (jung_seong + 0))) * 28 + 0xAC00);
        } else if (cho_seong != null && jung_seong != null && jong_seong != null) {
            return (char) ((CHO_SEONG.indexOf((char) (cho_seong + 0)) * 21 + JUNG_SEONG.indexOf((char) (jung_seong + 0))) * 28 + JONG_SEONG.indexOf((char) (jong_seong + 0)) + 0xAC00);
        }
        return character;
    }

    public boolean hasJongSeong() {
        return jong_seong != null;
    }

    public void setChoSeong(int cho) {
        cho_seong = cho;
    }
}
