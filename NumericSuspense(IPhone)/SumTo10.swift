//
//  SumTo10.swift
//  Numeric Suspense
//
//  Created by Student on 6/8/17.
//  Copyright © 2017 Student. All rights reserved.
//

import UIKit

class SumTo10: UIViewController{

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
    var wrongTimer = Timer()
    var highScore = 0
    var tensList = [[1,9],[2,8],[3,7],[4,6],[5,5]]
    var tile1 = 0
    var tile2 = 0
    var val1 = 0, val2 = 0
    let redColor : UIColor = UIColor(red:0.84, green:0.00, blue:0.00, alpha:1.0)
    let yellowTile = UIColor(red: 1.00, green: 0.76, blue: 0.03, alpha: 1.0)
    let whiteTile =  UIColor(red: 1.00, green: 1.00, blue: 1.00, alpha: 1.00)
    
    var numberList =  [0,0,0,0]
    
    override func viewDidLoad() {
        super.viewDidLoad()

        timer = Timer.scheduledTimer(timeInterval: (incRandom / 1000.0), target: self, selector: #selector(Arithmetic.timePass), userInfo: nil, repeats: true)
        let highScoreDefault = UserDefaults()
    if(highScoreDefault.object(forKey: "SumTo10High") != nil){
    highScore = highScoreDefault.integer(forKey: "SumTo10High")
    }
        
    }
    
    override var shouldAutorotate: Bool{
        return false
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
        return UIInterfaceOrientationMask.portrait
    }
    
    override func viewWillAppear(_ animated: Bool) {
    
        topLeftTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        topLeftTile.tag = 1
        topRightTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        topRightTile.tag = 2
        bottomLeftTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        bottomLeftTile.tag = 3
        bottomRightTile.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(Click)))
        bottomRightTile.tag = 4
        
        scoreLabel.text = "Score: 0"
        RandomNumberList()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    
    func Click(sender : UITapGestureRecognizer) {
        switch (sender.view?.tag)!{
        case 1:
            if(tile1 == 0){
                topLeftTile.layer.borderWidth = 2
                topLeftTile.layer.borderColor = UIColor.black.cgColor
                tile1 = 1
                val1 = numberList[0]
            }else if(tile2 == 0 && (sender.view?.tag)! != tile1){
                topLeftTile.layer.borderWidth = 2
                topLeftTile.layer.borderColor = UIColor.black.cgColor
                tile2 = 1
                val2 = numberList[0]
                if(val1 + val2 == 10){
                     _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                }else{
                    topLeftTile.backgroundColor = redColor
                    _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                    
                }
            }
        case 2:
            if(tile1 == 0){
                topRightTile.layer.borderWidth = 2
                topRightTile.layer.borderColor = UIColor.black.cgColor
                tile1 = 2
                val1 = numberList[1]
            }else if(tile2 == 0  && (sender.view?.tag)! != tile1){
                topRightTile.layer.borderWidth = 2
                topRightTile.layer.borderColor = UIColor.black.cgColor
                tile2 = 2
                val2 = numberList[1]
                if(val1 + val2 == 10){
                     _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                }else{
                    topRightTile.backgroundColor = redColor
                    _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                }
            }
        case 3:
            if(tile1 == 0){
                bottomLeftTile.layer.borderWidth = 2
                bottomLeftTile.layer.borderColor = UIColor.black.cgColor
                tile1 = 3
                val1 = numberList[2]
            }else if(tile2 == 0  && (sender.view?.tag)! != tile1){
                bottomLeftTile.layer.borderWidth = 2
                bottomLeftTile.layer.borderColor = UIColor.black.cgColor
                tile2 = 3
                val2 = numberList[2]
                if(val1 + val2 == 10){
                     _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                }else{
                    bottomLeftTile.backgroundColor = redColor
                    _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                }
            }
        case 4:
            if(tile1 == 0){
                bottomRightTile.layer.borderWidth = 2
                bottomRightTile.layer.borderColor = UIColor.black.cgColor
                tile1 = 4
                val1 = numberList[3]
            }else if(tile2 == 0  && (sender.view?.tag)! != tile1){
                bottomRightTile.layer.borderWidth = 2
                bottomRightTile.layer.borderColor = UIColor.black.cgColor
                tile2 = 4
                val2 = numberList[3]
                if(val1 + val2 == 10){
                    _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                }else{
                    bottomRightTile.backgroundColor = redColor
                    _ = Timer.scheduledTimer(timeInterval: 0.1, target: self, selector: #selector(Tile), userInfo: nil, repeats: false)
                }
            }
        default:
            print("fail")
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
        time += 1
    }
    
    func subtractTime(){
            time = time - 1
    }

    func RandomNumberList(){
        let ranNum = Int(arc4random_uniform(5))
        if(ranNum == 0){
           numberList = [tensList[ranNum][0],tensList[ranNum][1],tensList[ranNum + 1][Int(arc4random_uniform(2))],tensList[ranNum + 2][Int(arc4random_uniform(2))]]
        }else if(ranNum == 4){
             numberList = [tensList[ranNum][0],tensList[ranNum][1],tensList[ranNum - 1][Int(arc4random_uniform(2))],tensList[ranNum - 2][Int(arc4random_uniform(2))]]
        }else{
             numberList = [tensList[ranNum][0],tensList[ranNum][1],tensList[ranNum + 1][Int(arc4random_uniform(2))],tensList[ranNum - 1][Int(arc4random_uniform(2))]]
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
        setTiles()
    }
    
    func setTiles(){
        topLeftTile.layer.borderWidth = 0
        topRightTile.layer.borderWidth = 0
        bottomLeftTile.layer.borderWidth = 0
        bottomRightTile.layer.borderWidth = 0
        
        
        tile1 = 0
        tile2 = 0
        
        topLeftTile.backgroundColor = yellowTile
        topRightTile.backgroundColor = whiteTile
        bottomLeftTile.backgroundColor = whiteTile
        bottomRightTile.backgroundColor = yellowTile
    }
    
    func Tile(){
        if(val1 + val2 == 10){
            score+=1
            scoreLabel.text = "Score: \(score)"
            RandomNumberList()
            addTime()
        }else{
        subtractTime()
        setTiles()
        }
    }
    
    func CheckHighScore(){
        if score > highScore {
            let highScoreDefault = UserDefaults()
            highScore = score
            highScoreDefault.set(score, forKey: "SumTo10High")
            highScoreDefault.synchronize()
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        let controller = segue.destination as! EndGame
        controller.score = score
        controller.reason = "You Ran Out Of Time"
        controller.type = "SumTo10"
        controller.Highscore = highScore
    }
    
    func endGame(){
        CheckHighScore()
        performSegue(withIdentifier: "endGame", sender: self)
    }
    

}
