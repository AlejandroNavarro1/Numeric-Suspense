//
//  EndGame.swift
//  Numeric Suspense
//
//  Created by Student on 5/10/17.
//  Copyright © 2017 Student. All rights reserved.
//

import UIKit

class EndGame: UIViewController {

    public var type : String = ""
    public var reason : String = ""
    public var Highscore : Int = 0
    public var score : Int = 0
    
    @IBOutlet var typeLabel: UILabel!
    @IBOutlet var reasonLabel: UILabel!
    @IBOutlet var highScoreLabel: UILabel!
    @IBOutlet var scoreLabel: UILabel!
    @IBOutlet var menuButton: UIButton!
    @IBOutlet var againButton: UIButton!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    override var shouldAutorotate: Bool{
        return false
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask{
        return UIInterfaceOrientationMask.portrait
    }
    
    override func viewWillAppear(_ animated: Bool) {
        
        //menuButton.titleLabel?.adjustsFontSizeToFitWidth = true
       // againButton.titleLabel?.adjustsFontSizeToFitWidth = true
        
        typeLabel.text = type
        reasonLabel.text = reason
        highScoreLabel.text = "High Score: \(Highscore)"
        scoreLabel.text = "\(score)"
        
        menuButton.layer.cornerRadius = 15
        menuButton.layer.borderWidth = 2
        menuButton.titleEdgeInsets = UIEdgeInsetsMake(5, 5, 5, 5)
        let color = UIColor(red:0.38, green:0.49, blue:0.55, alpha:1.0)
        menuButton.layer.borderColor = color.cgColor
        
        againButton.titleEdgeInsets = UIEdgeInsetsMake(5, 5, 5, 5)
        againButton.layer.cornerRadius = 15
        againButton.layer.borderWidth = 2
        againButton.layer.borderColor = color.cgColor
        
    }
    
    @IBAction func menuButtonPressed(_ sender: Any) {
        performSegue(withIdentifier: "mainMenu", sender: self)
    }
    
    @IBAction func againButtonPressed(_ sender: Any) {
        performSegue(withIdentifier: "again\(type)", sender: self)
    }

}
