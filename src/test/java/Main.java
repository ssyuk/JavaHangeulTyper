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
        engKorMap.put('\b', '\b');

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
                text.append(engKorMap.get(e.getKeyChar()));
                label.setText(text.toString());
            }
        });
    }
}
