//
//  GameListTableViewCell.swift
//  Numeric Suspense
//
//  Created by Student on 5/30/17.
//  Copyright © 2017 Student. All rights reserved.
//

import UIKit

class GameListTableViewCell: UITableViewCell {

    @IBOutlet var gameButton: UILabel!
    @IBOutlet var questionButton: UIImageView!
    @IBOutlet var labelButton: UILabel!
    @IBOutlet weak var infoLabel: UILabel!
    
    let labelc = "(Click The Colored Text To Play)"
    let labelList = ["Click each number tile in ascending order by counting up by 1. But beware because the numbers on the number tiles may change position.", "Click two number tiles that add up to 10. "]
    var value: Int = 0
    
    override func awakeFromNib() {
        super.awakeFromNib()
        
        
        infoLabel.backgroundColor = UIColor.white
        infoLabel.frame.size.height = 0
        labelButton.text = labelc
        let questionPress = UITapGestureRecognizer(target: self, action: #selector(questionButtonWasPressed))
        questionButton.addGestureRecognizer(questionPress)
        let labelPress = UITapGestureRecognizer(target: self, action: #selector(LabelWasPressed))
        infoLabel.addGestureRecognizer(labelPress)
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
        // Configure the view for the selected state
    }
    
    func setVal(x: Int){
       value = x
    }
    
    func questionButtonWasPressed(sender : UITapGestureRecognizer){

            infoLabel.text = labelList[value]
    }
    
    func LabelWasPressed(sender : UITapGestureRecognizer){
            infoLabel.text = ""
            infoLabel.frame.size.height = 0
    }
    
}
