//
//  Arithmetic.swift
//  Numeric Suspense
//
//  Created by Student on 3/30/17.
//  Copyright © 2017 Student. All rights reserved.
//

import UIKit

class Arithmetic: UIViewController{

    var number = 1
    @IBOutlet var topLeftTile: UILabel!
    @IBOutlet var topRightTile: UILabel!
    @IBOutlet var bottomLeftTile: UILabel!
    @IBOutlet var bottomRightTile: UILabel!
    @IBOutlet var scoreLabel : UILabel!
    @IBOutlet var timeLabel : UILabel!
    
    var score = 0
    var time = 5
    var milli = 1000
    let incRandom : Double = Double(arc4random_uniform(50) + 100)
    var timer = Timer()
    var highScore = 0
    
    var numberList =  [0,0,0,0]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        timer = Timer.scheduledTimer(timeInterval: (incRandom / 1000.0), target: self, selector: #selector(Arithmetic.timePass), userInfo: nil, repeats: true)
        
        let highScoreDefault = UserDefaults()
        if(highScoreDefault.object(forKey: "ArithmeticHigh") != nil){
            highScore = highScoreDefault.integer(forKey: "ArithmeticHigh")
        }
    }
    
    override func viewWillAppear(_ animated: Bool) {
        
        //let defaults = UserDefaults.standard
        
        //defaults.integer(forKey: "highscore")
        topLeftTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        topLeftTile.tag = 1
        topRightTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        topRightTile.tag = 2
        bottomLeftTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        bottomLeftTile.tag = 3
        bottomRightTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        bottomRightTile.tag = 4
        setList(number: number)
        scoreLabel.text = "Score: 0"
    }
    
    override var shouldAutorotate: Bool{
        return false
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
        return UIInterfaceOrientationMask.portrait
    }
    
    func increaseScore() {
        score += 1
        scoreLabel.text = " Score: \(score)"
    }
    
    func setList (number : Int){
        for tile in 0...3{
            numberList[tile] = number + tile
        }
        for tile in 0...3{
            let random = Int(arc4random_uniform(4))
            let tempNum = numberList[random]
            numberList[random] = numberList[tile]
            numberList[tile] = tempNum
        }
        topLeftTile.text = "\(numberList[0])"
        topRightTile.text = "\(numberList[1])"
        bottomLeftTile.text = "\(numberList[2])"
        bottomRightTile.text = "\(numberList[3])"
    }
    
    func Click(sender : UITapGestureRecognizer) {
        switch (sender.view?.tag)!{
        case 1:
            let count = Int((topLeftTile.text)!)
            if count == number{
            increaseScore()
            number += 1
            addTime()
            setList(number: number)
            }else{
             timer.invalidate()
                endGame()
        }
        case 2:
            let count = Int((topRightTile.text)!)
            if count == number{
            increaseScore()
            number += 1
            addTime()
            setList(number: number)
            }else{
                timer.invalidate()
                endGame()
            }
        case 3:
            let count = Int((bottomLeftTile.text)!)
            if count == number{
            increaseScore()
            number += 1
            addTime()
            setList(number: number)
            }else{
                timer.invalidate()
                endGame()
            }
    
        case 4:
            let count = Int((bottomRightTile.text)!)
            if count == number{
            increaseScore()
            number += 1
            addTime()
            setList(number: number)
            }else{
                timer.invalidate()
                endGame()
            }
        default:
            print("error occured!")
        }
    }
    
    
    func timePass(){
        if milli > 0 || time >= 0 {
            if milli == 0{
                milli = 1000
                time = time - 1
            }
            milli = milli - Int(incRandom)
            if milli < 0{
                time = time - 1
                milli = 1000 + milli
            }
            if time < 0{
                time = 0
                milli = 0
                timer.invalidate()
                endGame()
            }
            timeLabel.text = "Time: \(time).\(milli)"
        }
    }
    
    func addTime(){
        if milli + 500 > 1000{
          time = time + 1
          milli = (milli + 500) - 1000
        }else{
            milli = milli + 500
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let controller = segue.destination as! EndGame
        controller.score = score
        if(!timer.isValid && time == 0){
            controller.reason = "You Ran Out Of Time"
        }else{
           controller.reason = "You Tapped the Wrong Tile"
        }
        controller.type = "Arithmetic"
        controller.Highscore = highScore
    }
    
    func endGame(){
        CheckHighScore()
        performSegue(withIdentifier: "endGame", sender: self)
    }
    
    func CheckHighScore(){
        if score > highScore {
            let highScoreDefault = UserDefaults()
            highScore = score
            highScoreDefault.set(score, forKey: "ArithmeticHigh")
            highScoreDefault.synchronize()
        }
    }


}

