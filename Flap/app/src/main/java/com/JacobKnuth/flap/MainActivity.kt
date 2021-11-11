package com.JacobKnuth.flap

import android.content.Context
import android.content.SharedPreferences
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.JacobKnuth.flap.databinding.ActivityMainBinding
import com.JacobKnuth.flap.databinding.SettBinding


class MainActivity : AppCompatActivity() {



    lateinit var d: String
    lateinit var binder: ActivityMainBinding
    lateinit var game:gamePlay
    lateinit var die:MediaPlayer
    lateinit var jump:MediaPlayer
    lateinit var pass:MediaPlayer
    var isMute = false
    lateinit var dataStore:SharedPreferences
    lateinit var editor:SharedPreferences.Editor
    lateinit var highScoreView:TextView

    //settings
    lateinit var binderSettings: SettBinding

    lateinit var colorsBones: ArrayList<Button>
    lateinit var colorsBackround: java.util.ArrayList<Button>

    var w = 0f
    var h = 0f

    lateinit var boneColor:String
    lateinit var backround:String




    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game =  gamePlay(this)
        binder = ActivityMainBinding.inflate(layoutInflater)
        this.die = MediaPlayer.create(this, R.raw.howl)
        this.die.setVolume(1f, 1f);
        this.jump = MediaPlayer.create(this, R.raw.jump)
        this.jump.setVolume(.3f,.3f)
        this.pass = MediaPlayer.create(this, R.raw.passed)

        setContentView(binder.root)
         this.dataStore = getPreferences(Context.MODE_PRIVATE)
        this.editor = dataStore.edit()

        highScoreView = TextView(this)
        highScoreView.background = binder.textView.background
        boneColor = dataStore.getString("boneColor", "-65536").toString()
        backround = dataStore.getString("background", "-2").toString()




        binder.root.addView(highScoreView)


        //settting
        binderSettings = SettBinding.inflate(layoutInflater)
        colorsBackround = ArrayList<Button>()
        colorsBones = ArrayList()


        var r = Runnable {
            while (true){
                this.w = binderSettings.root.width.toFloat()
                this.h = binderSettings.root.height.toFloat()
                if (this.w != 0f && this.h != 0f){

                    runOnUiThread(Runnable {

                        loadSettings()
                    })

                    break
                }
            }

        }

        var t = Thread(r)
        t.start()






        binder.Start.setOnClickListener {

            game.start()

            setContentView(game)


        }
        binder.settings.setOnClickListener {
            setContentView(binderSettings.root)

            //loadSettings()
        }

        binder.mute.setOnClickListener {
            if (isMute){
                isMute = false
                unMute()
                binder.mute.setText("MUTE")
            }
            else{
                isMute = true
                mute()
                binder.mute.setText("UN-MUTE")
            }
        }

    }
    fun back() {
        game.end()
        setContentView(binder.root)
        this.game  = gamePlay(this)
        binder.textView.setText("You passed " + d +" bones!")
        var high = dataStore.getInt("highScore", 0)


        if (d.toInt() > high){
            editor.putInt("highScore", d.toInt())
            editor.commit()
            highScoreView.setText("New High Score of "+ d)


        }
        else{
            highScoreView.setText("High Score of "+ high.toString())
        }
        binder.Start.setText("AGAIN?")


        //startActivity(new Intent(this, MainActivity.class));
        //this.finish();
    }
    fun mute(){

        this.die.setVolume(0f, 0f);

        this.jump.setVolume(0f,0f)
        this.pass.setVolume(0f,0f);
    }
    fun unMute(){
        this.die.setVolume(1f, 1f);

        this.jump.setVolume(.3f,.3f)
        this.pass.setVolume(1f,1f);
    }


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun loadSettings(){
        colorsBackround.add(binderSettings.blue)
        colorsBackround.add(binderSettings.red)
        colorsBackround.add(binderSettings.green)
        colorsBackround.add(binderSettings.white)

        colorsBones.add(binderSettings.Blue)
        colorsBones.add(binderSettings.Red)
        colorsBones.add(binderSettings.Green)
        colorsBones.add(binderSettings.White)

        binderSettings.textView2.setTextSize(30f)
        binderSettings.textView4.setTextSize(30f)
        binderSettings.button5.setOnClickListener {
            setContentView(binder.root)
        }

        for ( b in colorsBackround){
            b.setText("")
            b.setOnClickListener {
                backround = b.backgroundTintList?.getDefaultColor().toString()
                for ( i in colorsBackround){
                    i.setText("")

                }
                b.setText("Selected")
                editor.putString("background", backround)
                editor.commit()
            }
        }

        for ( b in colorsBones){
            b.setText("")
            b.setOnClickListener {
                boneColor = b.backgroundTintList?.getDefaultColor().toString()
                for ( i in colorsBones){
                    i.setText("")
                }
                b.setText("Selected")
                editor.putString("boneColor", boneColor)
                editor.commit()
            }









        }


    }


}