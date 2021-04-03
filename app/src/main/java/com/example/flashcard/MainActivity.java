package com.example.flashcard;

import android.animation.Animator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;



public class MainActivity extends AppCompatActivity {
    private TextView questionSideView;
    private TextView answerSideView;

    FlashcardDatabase flashcardDatabase;
    List<Flashcard> allFlashcards;
    int currentCardDisplayedIndex = 0;
    final String[] questionAnswer = {"When was the first version of Android released?", "2008"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView answerTextView = findViewById(R.id.answer);
        TextView questionTextView = findViewById(R.id.question);
        TextView choiceOne = findViewById(R.id.choiceOne);
        TextView choiceTwo = findViewById(R.id.choiceTwo);
        TextView choiceThree = findViewById(R.id.choiceThree);
        ImageView toggle = findViewById(R.id.toggle);
        Button buttonR = findViewById(R.id.buttonR);
        ImageView add = findViewById(R.id.add);
        ImageView trash = findViewById(R.id.deleteBtn);
        flashcardDatabase = new FlashcardDatabase(getApplicationContext());

        questionSideView = findViewById(R.id.question);
        answerSideView = findViewById(R.id.answer);

        flashcardDatabase = new FlashcardDatabase(this);
        allFlashcards = flashcardDatabase.getAllCards();
        if (allFlashcards != null && allFlashcards.size() > 0) {
            ((TextView) findViewById(R.id.question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());
        }

        /*
        if (flashcardDatabase.getAllCards().size() > 0){
            ((TextView) findViewById(R.id.question)).setText(allFlashcards.get(0).getQuestion());
            ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());

        }*/

        currentCardDisplayedIndex = 0;
        Flashcard flashcard = flashcardDatabase.getAllCards().get(0);
        String question = flashcard.getQuestion();
        questionSideView.setText(question);
        String answer = flashcard.getAnswer();
        answerSideView.setText(answer);



        //for the button.
        final boolean[] showAnswers = {true};
        final boolean[] flipped = {false};


        // change the background color of the multiple choice answers when
        // clicked to indicate whether the question was answered correctly
        choiceOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceOne.setBackgroundColor(getResources().getColor(R.color.green, null));
            }
        });
        choiceTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceOne.setBackgroundColor(getResources().getColor(R.color.green, null));
                choiceTwo.setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });
        choiceThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceOne.setBackgroundColor(getResources().getColor(R.color.green, null));
                choiceThree.setBackgroundColor(getResources().getColor(R.color.red, null));
            }
        });


        //question and answer toggle
        questionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionTextView.setVisibility(View.INVISIBLE);
                answerTextView.setVisibility(View.VISIBLE);
            }
        });

        answerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                questionTextView.setVisibility(View.VISIBLE);
                answerTextView.setVisibility(View.INVISIBLE);
            }
        });


        // added a button so users can easily reset the correct and incorrect choices.
        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                choiceOne.setBackgroundColor(getResources().getColor(R.color.pink, null));
                choiceTwo.setBackgroundColor(getResources().getColor(R.color.pink, null));
                choiceThree.setBackgroundColor(getResources().getColor(R.color.pink, null));
                questionTextView.setText("When was the first version of Android released?");
                flipped[0] = false;
            }
        });



        // to show the choices and hide the choices
        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (showAnswers[0]) {
                    showAnswers[0] = false;
                    choiceOne.setVisibility(View.INVISIBLE);
                    choiceTwo.setVisibility(View.INVISIBLE);
                    choiceThree.setVisibility(View.INVISIBLE);
                    toggle.setImageResource(R.drawable.eye_hidden);

                } else {
                    showAnswers[0] = true;
                    choiceOne.setVisibility(View.VISIBLE);
                    choiceTwo.setVisibility(View.VISIBLE);
                    choiceThree.setVisibility(View.VISIBLE);
                    toggle.setImageResource(R.drawable.eye_open);
                }
            }
        });


            final Animation leftOutAnim = AnimationUtils.loadAnimation(this, R.anim.left_out);
            final Animation rightInAnim = AnimationUtils.loadAnimation(this, R.anim.right_in);

            leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {

                    findViewById(R.id.question).startAnimation(rightInAnim);
                    if (currentCardDisplayedIndex < allFlashcards.size()-1) {
                        currentCardDisplayedIndex++; // advance our pointer index so we can show the next card
                    }
                    else {
                        currentCardDisplayedIndex=0; // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                    }
                    Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                    ((TextView) findViewById(R.id.question)).setText(flashcard.getQuestion());
                    ((TextView) findViewById(R.id.answer)).setText(flashcard.getAnswer());
                }
                    //((TextView) findViewById(R.id.question)).setText(allFlashcards.get(0).getQuestion());
                   // ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());
                //}

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });


        findViewById(R.id.nextBtn).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                if (allFlashcards.size() == 0)
                    return;
                findViewById(R.id.question).startAnimation(leftOutAnim);


                // set the question and answer TextViews with data from the database

            }});

                /*
                final Animation leftOutAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.left_out);
                final Animation rightInAnim = AnimationUtils.loadAnimation(v.getContext(), R.anim.right_in);

                leftOutAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        ((TextView) findViewById(R.id.question)).setText(allFlashcards.get(0).getQuestion());
                        ((TextView) findViewById(R.id.answer)).setText(allFlashcards.get(0).getAnswer());
                        
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

             */

                /*
                // don't try to go to next card if you have no cards to begin with
                if (allFlashcards.size() == 0) {
                    return;
                }
                currentCardDisplayedIndex++;
                if (currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(v,
                            "You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;
                    questionAnswer[0] = allFlashcards.get(0).getQuestion();
                    questionAnswer[1] = allFlashcards.get(0).getAnswer();

                } else {
                    questionAnswer[0] = allFlashcards.get(currentCardDisplayedIndex).getQuestion();
                    questionAnswer[1] = allFlashcards.get(currentCardDisplayedIndex).getAnswer();
                }
                flashcard.startAnimation(leftOutAnim);


            }
        });*/



                /*
                // advance our pointer index so we can show the next card
                currentCardDisplayedIndex++;

                // make sure we don't get an IndexOutOfBoundsError if we are viewing the last indexed card in our list
                if(currentCardDisplayedIndex >= allFlashcards.size()) {
                    Snackbar.make(questionSideView,
                            "You've reached the end of the cards, going back to start.",
                            Snackbar.LENGTH_SHORT)
                            .show();
                    currentCardDisplayedIndex = 0;
                }

                // set the question and answer TextViews with data from the database
                allFlashcards = flashcardDatabase.getAllCards();
                Flashcard flashcard = allFlashcards.get(currentCardDisplayedIndex);

                ((TextView) findViewById(R.id.question)).setText(flashcard.getAnswer());
                ((TextView) findViewById(R.id.answer)).setText(flashcard.getQuestion());
            }
        });


                 */


        findViewById(R.id.question).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // findViewById(R.id.flashcard_hint).setVisibility(View.VISIBLE);
                // findViewById(R.id.flashcard_question).setVisibility(View.INVISIBLE);
                View questionSideView = findViewById(R.id.question);

// get the center for the clipping circle
                int cx = answerSideView.getWidth() / 2;
                int cy = answerSideView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim = ViewAnimationUtils.createCircularReveal(answerSideView, cx, cy, 0f, finalRadius);

// hide the question and show the answer to prepare for playing the animation!
                questionSideView.setVisibility(View.INVISIBLE);
                answerSideView.setVisibility(View.VISIBLE);

                anim.setDuration(3000);
                anim.start();
            }
        });



        findViewById(R.id.deleteBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashcardDatabase.deleteCard(allFlashcards.get(currentCardDisplayedIndex).getQuestion());
                allFlashcards = flashcardDatabase.getAllCards();
            }
        });




        findViewById(R.id.add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
                MainActivity.this.startActivity(intent); overridePendingTransition(R.anim.right_in, R.anim.left_out);
            }

        });

        Intent intent = new Intent(MainActivity.this, AddCardActivity.class);
        intent.putExtra("question", " ");
        intent.putExtra("answer", " ");
        MainActivity.this.startActivityForResult(intent, 100);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            String question = data.getExtras().getString("question");
            String answer = data.getExtras().getString("answer");

            ((TextView) findViewById(R.id.question)).setText(question);
            ((TextView) findViewById(R.id.answer)).setText(answer);


            flashcardDatabase.insertCard(new Flashcard(question, answer));
            allFlashcards = flashcardDatabase.getAllCards();




        }
    }

}

