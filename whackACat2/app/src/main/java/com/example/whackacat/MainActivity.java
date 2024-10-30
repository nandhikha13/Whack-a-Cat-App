package com.example.whackacat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    static ImageView cat1; static ImageView cat2; static ImageView cat3; static ImageView cat4;
    static ImageView whippedcream; static ImageView catbomb; static TextView timer;
    static TextView scoreText; static TextView temp; TextView gameOverText;
    Button restart; private static int score = 0; private static long time = 60500;
    private static boolean isRunning = false; private static long startTime; private static boolean powerUp = false;
    private static boolean powerDown = false; private static boolean isGameOver = false; private static int targetHit = -1;
    private int totaltallymarks = 0; private final int rowtallymarks = 30; private final int tallymarkspacing = 20;
    private final int tallymarkwidth = 21; private final int tallymarkheight = 99; List<ImageView> tallyMarks = new ArrayList<>();
    private int lastTallyMarkId = View.NO_ID; ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cat1 = findViewById(R.id.imageView);
        cat2 = findViewById(R.id.imageView4);
        cat3 = findViewById(R.id.imageView5);
        cat4 = findViewById(R.id.imageView6);
        whippedcream = findViewById(R.id.imageView2);
        catbomb = findViewById(R.id.imageView3);
        timer = findViewById(R.id.textView);
        scoreText = findViewById(R.id.textView2);
        restart = findViewById(R.id.button);
        gameOverText = findViewById(R.id.textView3);
        layout = findViewById(R.id.layout);
        temp = findViewById(R.id.temp);

        cat1.setVisibility(View.GONE);
        cat2.setVisibility(View.GONE);
        cat3.setVisibility(View.GONE);
        cat4.setVisibility(View.GONE);
        whippedcream.setVisibility(View.GONE);
        catbomb.setVisibility(View.GONE);
        gameOverText.setVisibility(View.GONE);
        gameOverText.setEnabled(false);

        startGame();

        cat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                scoreText.setText("Score:\n" + score);
                targetHit = 0;
                hideTargets(0);
                cat1.setEnabled(false);
                powerUp = false;
                powerDown = false;
                addTallyMark();
            }
        });

        cat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                scoreText.setText("Score:\n" + score);
                targetHit = 1;
                hideTargets(1);
                cat2.setEnabled(false);
                powerUp = false;
                powerDown = false;
                addTallyMark();
            }
        });

        cat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                scoreText.setText("Score:\n" + score);
                targetHit = 2;
                hideTargets(2);
                cat3.setEnabled(false);
                powerUp = false;
                powerDown = false;
                addTallyMark();
            }
        });

        cat4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score++;
                scoreText.setText("Score:\n" + score);
                targetHit = 3;
                hideTargets(3);
                cat4.setEnabled(false);
                powerUp = false;
                powerDown = false;
                addTallyMark();
            }
        });

        whippedcream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerUp = true;
                powerDown = false;
                targetHit = 4;
                hideTargets(4);
                whippedcream.setEnabled(false);
                startCountDownTimer();
                addTallyMark();
            }
        });

        catbomb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                powerDown = true;
                powerUp = false;
                targetHit = 5;
                hideTargets(5);
                catbomb.setEnabled(false);
                startCountDownTimer();
                addTallyMark();
            }
        });

        powerUp = false;
        powerDown = false;
        targetHit = -1;
        startCountDownTimer();

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                endGame();

                for (ImageView tallyMark : tallyMarks) {
                    layout.removeView(tallyMark);
                }
                tallyMarks.clear();
                gameOverText.setVisibility(View.GONE);
                gameOverText.setEnabled(false);
                startGame();
                startCountDownTimer();
            }
        });
    }

    public synchronized void showTargets(int rand)
    {
        ImageView target = null;

        switch (rand) {
            case 0:
                target = cat1;
                break;
            case 1:
                target = cat2;
                break;
            case 2:
                target = cat3;
                break;
            case 3:
                target = cat4;
                break;
            case 4:
                target = whippedcream;
                break;
            case 5:
                target = catbomb;
                break;
        }

        if (target != null) {
            ImageView finalTarget = target;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    finalTarget.setVisibility(View.VISIBLE);
                    final ScaleAnimation scaleAnimation = new ScaleAnimation(0f, 1.0f, 0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(1500);
                    scaleAnimation.setFillAfter(true);
                    finalTarget.setEnabled(true);
                    finalTarget.startAnimation(scaleAnimation);
                }
            });
        }
    }

    public synchronized void hideTargets(int rand)
    {
        ImageView target = null;

        switch (rand) {
            case 0:
                target = cat1;
                break;
            case 1:
                target = cat2;
                break;
            case 2:
                target = cat3;
                break;
            case 3:
                target = cat4;
                break;
            case 4:
                target = whippedcream;
                break;
            case 5:
                target = catbomb;
                break;
        }

        if (target != null) {
            ImageView finalTarget = target;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    final ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0f, 1.0f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scaleAnimation.setDuration(1500);
                    scaleAnimation.setFillAfter(true);
                    finalTarget.setEnabled(false);
                    finalTarget.startAnimation(scaleAnimation);
                    finalTarget.setVisibility(View.GONE);
                }
            });
        }
    }

    public static synchronized void addTime()
    {
        time += 5000;
        powerUp = false;
    }

    public static synchronized void subtractTime()
    {
        time -= 5000;
        powerDown = false;
    }

    public void startGame()
    {
        Thread TargetThread = new Thread() {
            public void run()
            {
                while(!isGameOver) {
                    int rand = (int) (Math.random() * 6);
                    showTargets(rand);
                    targetHit = -1;

                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                    }
                    if(targetHit == -1)
                        hideTargets(rand);
                }

                synchronized (this) {
                    if (isGameOver) {
                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        };

        if (isGameOver)
            TargetThread.interrupt();

        if (!TargetThread.isAlive()) {
            TargetThread.start();
        }

        isGameOver = false;
        powerUp = false;
        powerDown = false;
        time = 60500;
        score = 0;
        scoreText.setText("Score:\n" + score);
    }
    public void endGame()
    {
        isGameOver = true;
        gameOverText.setEnabled(true);
        cat1.setEnabled(false);
        cat2.setEnabled(false);
        cat3.setEnabled(false);
        cat4.setEnabled(false);
        whippedcream.setEnabled(false);
        catbomb.setEnabled(false);
        cat1.setVisibility(View.GONE);
        cat2.setVisibility(View.GONE);
        cat3.setVisibility(View.GONE);
        cat4.setVisibility(View.GONE);
        whippedcream.setVisibility(View.GONE);
        catbomb.setVisibility(View.GONE);
        gameOverText.setVisibility(View.VISIBLE);
        gameOverText.setText("GAME OVER! \nScore: " + score);
        powerUp = false;
        powerDown = false;
    }

    public void startCountDownTimer() {

        if (isRunning) {
            return;
        }

        isRunning = true;
        startTime = System.currentTimeMillis();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                while (isRunning) {
                    try {
                        long elapsedTime = System.currentTimeMillis() - startTime;
                        long remainingTime = time - elapsedTime;

                        if (remainingTime <= 0) {
                            isRunning = false;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timer.setText("00:00");
                                    endGame();
                                }
                            });

                        } else {
                            NumberFormat f = new DecimalFormat("00");
                            long min = (remainingTime / 60000) % 60;
                            long sec = (remainingTime / 1000) % 60;
                            if (powerUp)
                                addTime();
                            if (powerDown)
                                subtractTime();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    timer.setText(f.format(min) + ":" + f.format(sec));
                                }
                            });
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        isRunning = false;
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

        public void addTallyMark() {
        ImageView tallyMark = new ImageView(this);
        tallyMark.setId(View.generateViewId());
        tallyMark.setImageResource(R.drawable.line);

        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(tallymarkwidth, tallymarkheight);
        tallyMark.setLayoutParams(params);

        layout.addView(tallyMark);
        tallyMarks.add(tallyMark);
        ConstraintSet newConstraints = new ConstraintSet();
        newConstraints.clone(layout);

        int row = totaltallymarks / rowtallymarks;
        int col = totaltallymarks % rowtallymarks;

        if (col == 0) {
            if (row == 0) {
                newConstraints.connect(tallyMark.getId(), ConstraintSet.TOP, temp.getId(), ConstraintSet.BOTTOM, tallymarkspacing);
            } else {
                newConstraints.connect(tallyMark.getId(), ConstraintSet.TOP, lastTallyMarkId, ConstraintSet.BOTTOM, tallymarkspacing);
            }
        } else {
            newConstraints.connect(tallyMark.getId(), ConstraintSet.TOP, lastTallyMarkId, ConstraintSet.TOP);
            newConstraints.connect(tallyMark.getId(), ConstraintSet.LEFT, lastTallyMarkId, ConstraintSet.RIGHT, tallymarkspacing);
        }

        newConstraints.applyTo(layout);

        lastTallyMarkId = tallyMark.getId();
        if(!isGameOver)
            totaltallymarks++;
        else
            totaltallymarks = 0;
    }
}