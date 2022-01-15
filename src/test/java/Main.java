import me.wincho.hangultyper.HangulText;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {
    private static final Map<Character, Character> engKorMap = new HashMap<>();
    private static final HangulText text = new HangulText("");
    private static boolean running = true;
    private static int frames = 0;

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

        JFrame frame = new JFrame();
        Canvas canvas = new Canvas();
        frame.add(canvas);
        frame.setSize(1000, 800);
        frame.setVisible(true);
        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                text.append(engKorMap.containsKey(e.getKeyChar()) ? engKorMap.get(e.getKeyChar()) : e.getKeyChar());
            }
        });

        new Thread(() -> {
            while (running) {
                Graphics graphics = canvas.getGraphics();

                long lastTime = System.nanoTime();
                long lastRender = System.nanoTime();
                double unprocessed = 0;
                int frames = 0;
                int ticks = 0;
                long lastTimer1 = System.currentTimeMillis();
                while (true) {
                    long now = System.nanoTime();
                    double nsPerTick = 1E9D / 60/*TPS*/;
                    unprocessed += (now - lastTime) / nsPerTick;
                    lastTime = now;
                    while (unprocessed >= 1) {
                        ticks++;
                        try {
//                            update();
                            Main.frames++;
                        } catch (Throwable e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "An error occurred while updating: " + e.getClass().getName() + ": " + e.getMessage());
                            System.exit(1);
                        }
                        unprocessed--;
                    }

                    if ((now - lastRender) / 1.0E9 > 1.0 / 60/*FPS*/) {
                        frames++;
                        lastRender = System.nanoTime();

                        // Start Render
                        try {
                            render(graphics, canvas.getWidth(), canvas.getHeight());
                        } catch (Throwable e) {
                            e.printStackTrace();
                            JOptionPane.showMessageDialog(null, "An error occurred while rendering: " + e.getClass().getName() + ": " + e.getMessage());
                            System.exit(1);
                        }
                        // End Render
                    }

                    if (System.currentTimeMillis() - lastTimer1 > 1000) {
                        lastTimer1 += 1000;

                        System.out.printf("%d FPS %d TPS%n", frames, ticks);

                        frames = 0;
                        ticks = 0;
                    }
                }
            }
        }, "Render Thread").start();
    }

    public static void render(Graphics graphics, int width, int height) {
        int y = 0;
        int lastX = 0;
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, width, height);

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Font.SERIF, Font.BOLD, 50));
        for (String s : text.toString().split("\n")) {
            graphics.drawString(s, 10, y += graphics.getFontMetrics().getHeight());
            lastX = graphics.getFontMetrics().stringWidth(s);
        }
        if (frames % 60 < 30)
            graphics.fillRect(lastX + 10, y - graphics.getFontMetrics().getHeight() + 15, 5, graphics.getFontMetrics().getHeight() - 5);
    }
}
