//
//  ProgressDataView.swift
//  Playground
//
//  Created by Fabian Heck on 16.12.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

class ProgressDataView: UIView {

    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var progressLabel: UILabel!
    @IBOutlet weak var progressBar: ProgressBar!
    
    var progress: Float = 0 {
        didSet {
            progressLabel.text = "\(Int(progress*100))%"
            progressBar.progress = progress
        }
    }
    
    @IBOutlet var contentView: UIView!

    override init(frame: CGRect) {
        super.init(frame: frame)
        setUpView()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setUpView()
    }
    
    func setUpView() {
        Bundle.main.loadNibNamed("ProgressDataView", owner: self, options: nil)
        addSubview(contentView)
        contentView.frame = self.bounds
        contentView.autoresizingMask = [.flexibleHeight, .flexibleWidth]
    }

}
