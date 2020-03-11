//
//  GameViewController.swift
//  Playground
//
//  Created by Fabian Heck on 26.12.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit
import SharedPlayground

class GameViewController : UIViewController, GameView {
    
    weak var coordinator: UseCasesTabCoordinator?
    var game: Game?
    @IBOutlet weak var gameExplanationLabel: UILabel!
    @IBOutlet var gameButtonCollection: [UIButton]!
    
    override func viewDidLoad() {
        self.title = NSLocalizedString("game_title", comment: "")
        gameExplanationLabel.text = NSLocalizedString("game_explanation", comment:"")
        setUpGame()
    }
    
    private func setUpGame() {
        game = Game(gameView: self)
        for i in 0...8 {
            gameButtonCollection[i].addTarget(self, action: #selector(buttonClicked), for: .touchUpInside)
        }
    }
    
    @objc func buttonClicked(_ sender: AnyObject?) {
        for i in 0...8 {
            if sender === gameButtonCollection[i] {
                game?.onNewStonePlaced(newStonePosition: Int32(i))
                break
            }
        }
    }
    
    func getColorForStone(stone: Int)-> UIColor {
        switch(stone) {
        case -1:
            return UIColor(red: 0.929, green: 0.463, blue: 0.353, alpha: 1)
        case 1:
            return UIColor(red: 0.102, green: 0.451, blue: 0.91, alpha: 1)
        default:
            return UIColor(red: 0.8, green: 0.8, blue: 0.8, alpha: 1)
        }
    }
    
    func invalidateBoard(board: KotlinArray) {
        var index: Int32 = 0
        gameButtonCollection.forEach{ button in
            let stone = board.get(index: index) as! Int
            button.backgroundColor = getColorForStone(stone: stone)
            index += 1
        }
    }
    
    func notify(message: String) {
        let alert = UIAlertController(title: "Game Over", message: message, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "Ok", style: .default, handler: nil))
        self.present(alert, animated: true)
    }
}
