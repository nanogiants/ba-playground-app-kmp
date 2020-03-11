//
//  ProgressBar.swift
//  Playground
//
//  Created by Fabian Heck on 16.12.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class ProgressBar : UIView {
    
    var progress: Float = 0 {
        didSet {
            setNeedsDisplay()
        }
    }
    
    // initializer if UIView is created in the code
    override init(frame: CGRect) {
        super.init(frame: frame)
        setUpView()
    }
    
    // initializer if the UIView comes out of a storyboard
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUpView()
    }
    
    func setUpView() {
        
    }
    
    override func draw(_ rect: CGRect) {
        super.draw(rect)
        drawBar(rect: rect, color: UIColor(#colorLiteral(red: 0.8980392157, green: 0.8980392157, blue: 0.8980392157, alpha: 1)))
        drawBar(rect: rect, color: UIColor(#colorLiteral(red: 0.2470588235, green: 0.5803921569, blue: 0.9764705882, alpha: 1)), progress: progress)
    }
    
    func drawBar(rect: CGRect, color: UIColor, progress: Float = 1) {
        let height = min(bounds.size.height, 10)
        let x = CGFloat(0)
        let y = rect.height/2.0-height/2
        var width = CGFloat(0)
        if (0...1).contains(progress) {
            width = bounds.size.width * CGFloat(progress)
        }
        
        let radius = CGFloat(height/2)
        let roundedRect = UIBezierPath(roundedRect: CGRect(x: x, y: y, width: width, height: height), cornerRadius: radius)
        color.setFill()
        roundedRect.fill()
    }
    
    override func draw(_ layer: CALayer, in ctx: CGContext) {
        super.draw(layer, in: ctx)
    }
    
    /*
     do sth with subviews when layout bounds change
     called by the system after setNeedsLayout
     */
    override func layoutSubviews() {
        super.layoutSubviews()
    }
    
}
