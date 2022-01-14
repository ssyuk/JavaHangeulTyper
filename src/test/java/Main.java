import me.wincho.hangultyper.HangulText;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
    private static final Map<Character, Character> engKorMap = new HashMap<>();

    public static void main(String[] args) {
        engKorMap.put('a', 'ㅁ');
        engKorMap.put('b', 'ㅠ');
        engKorMap.put('c', 'ㅊ');
        engKorMap.put('d', 'ㅇ');
        engKorMap.put('e', 'ㄷ');
        engKorMap.put('f', 'ㄹ');
        engKorMap.put('g', 'ㅎ');
        engKorMap.put('h', 'ㅗ');
        engKorMap.put('i', 'ㅑ');
        engKorMap.put('j', 'ㅓ');
        engKorMap.put('k', 'ㅏ');
        engKorMap.put('l', 'ㅣ');
        engKorMap.put('m', 'ㅡ');
        engKorMap.put('n', 'ㅜ');
        engKorMap.put('o', 'ㅐ');
        engKorMap.put('p', 'ㅔ');
        engKorMap.put('q', 'ㅂ');
        engKorMap.put('r', 'ㄱ');
        engKorMap.put('s', 'ㄴ');
        engKorMap.put('t', 'ㅅ');
        engKorMap.put('u', 'ㅕ');
        engKorMap.put('v', 'ㅍ');
        engKorMap.put('w', 'ㅈ');
        engKorMap.put('x', 'ㅌ');
        engKorMap.put('y', 'ㅛ');
        engKorMap.put('z', 'ㅋ');
        engKorMap.put("a".toUpperCase().charAt(0), 'ㅁ');
        engKorMap.put("b".toUpperCase().charAt(0), 'ㅠ');
        engKorMap.put("c".toUpperCase().charAt(0), 'ㅊ');
        engKorMap.put("d".toUpperCase().charAt(0), 'ㅇ');
        engKorMap.put("e".toUpperCase().charAt(0), 'ㄸ');
        engKorMap.put("f".toUpperCase().charAt(0), 'ㄹ');
        engKorMap.put("g".toUpperCase().charAt(0), 'ㅎ');
        engKorMap.put("h".toUpperCase().charAt(0), 'ㅗ');
        engKorMap.put("i".toUpperCase().charAt(0), 'ㅑ');
        engKorMap.put("j".toUpperCase().charAt(0), 'ㅓ');
        engKorMap.put("k".toUpperCase().charAt(0), 'ㅏ');
        engKorMap.put("l".toUpperCase().charAt(0), 'ㅣ');
        engKorMap.put("m".toUpperCase().charAt(0), 'ㅡ');
        engKorMap.put("n".toUpperCase().charAt(0), 'ㅜ');
        engKorMap.put("o".toUpperCase().charAt(0), 'ㅒ');
        engKorMap.put("p".toUpperCase().charAt(0), 'ㅖ');
        engKorMap.put("q".toUpperCase().charAt(0), 'ㅃ');
        engKorMap.put("r".toUpperCase().charAt(0), 'ㄲ');
        engKorMap.put("s".toUpperCase().charAt(0), 'ㄴ');
        engKorMap.put("t".toUpperCase().charAt(0), 'ㅆ');
        engKorMap.put("u".toUpperCase().charAt(0), 'ㅕ');
        engKorMap.put("v".toUpperCase().charAt(0), 'ㅍ');
        engKorMap.put("w".toUpperCase().charAt(0), 'ㅉ');
        engKorMap.put("x".toUpperCase().charAt(0), 'ㅌ');
        engKorMap.put("y".toUpperCase().charAt(0), 'ㅛ');
        engKorMap.put("z".toUpperCase().charAt(0), 'ㅋ');

        HangulText text = new HangulText("ㄱ");
        JFrame frame = new JFrame();
        JLabel label = new JLabel(text.toString());
        label.setFont(new Font(Font.SERIF, Font.BOLD, 100));
        frame.setSize(1000, 800);
        frame.add(label);
        frame.setVisible(true);
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                text.append(engKorMap.containsKey(e.getKeyChar()) ? engKorMap.get(e.getKeyChar()) : e.getKeyChar());
                label.setText(text.toString());
            }
        });
    }
}
