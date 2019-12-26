//
//  UseCasesViewController+CollectionView.swift
//  Playground
//
//  Created by Fabian Heck on 04.11.19.
//  Copyright © 2019 appcom interactive GmbH. All rights reserved.
//

import Foundation
import UIKit

extension UseCasesViewController : UICollectionViewDelegate {
    
    // onClick
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        print("You selected cell #\(indexPath.item)!")
        switch useCase(for: indexPath).id {
        case .Pixelsort:
            coordinator?.navigateToPixelsort()
            break
        case .Locating:
            break
        case .Nasa:
            coordinator?.navigateToNasa()
            break
        case .Notes:
            coordinator?.navigateToNotes()
            break
        case .Settings:
            coordinator?.navigateToSettings()
            break
        case .Fibonacci:
            coordinator?.navigateToFibonacci()
            break
        case .Game:
            coordinator?.navigateToGame()
            break
        }
    }
}

extension UseCasesViewController: UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return items.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        // recycle an already created cell or create a new one
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: UseCaseCell.identifier, for: indexPath)
        
        // style cell
        styleCell(uiCollectionCell: cell)
        
        // bind data
        if let useCaseCell = cell as? UseCaseCell {
            return bindView(useCase(for: indexPath), to: useCaseCell)
        } else {
            return cell
        }
    }
    
    func useCase(for indexPath: IndexPath) -> UseCase {
        return items[indexPath.item]
    }
    
    func bindView(_ useCase: UseCase, to useCaseCell: UseCaseCell) -> UseCaseCell {
        //        useCaseCell.contentView.backgroundColor = UIColor(named: String)
        //        useCaseCell.contentView.backgroundColor = UIColor.gray
        useCaseCell.contentView.backgroundColor = useCase.color
        useCaseCell.titleLabel.text = useCase.title
        useCaseCell.descriptionLabel.text = useCase.description
        
        useCaseCell.imageView.image = UIImage(named: useCase.imageName)
        // TODO
        useCaseCell.titleLabel.preferredMaxLayoutWidth = 290
        useCaseCell.descriptionLabel.preferredMaxLayoutWidth = 290
        
        
        return useCaseCell
    }
    
    func styleCell(uiCollectionCell: UICollectionViewCell) {
        uiCollectionCell.contentView.layer.cornerRadius = 8.0
        uiCollectionCell.contentView.layer.masksToBounds = true
        uiCollectionCell.layer.shadowColor = UIColor.lightGray.cgColor
        uiCollectionCell.layer.shadowOffset = .zero
        uiCollectionCell.layer.shadowRadius = 7.0 // how wide
        uiCollectionCell.layer.shadowOpacity = 0.4
        uiCollectionCell.layer.masksToBounds = false
        // generating shadow dynamically is expensive
        uiCollectionCell.layer.shadowPath = UIBezierPath(roundedRect: uiCollectionCell.bounds, cornerRadius: uiCollectionCell.contentView.layer.cornerRadius).cgPath
    }
}

extension UseCasesViewController: UICollectionViewDelegateFlowLayout {
    // tells the layout the size of the given cell
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        sizeForItemAt indexPath: IndexPath) -> CGSize {
        //2
        let paddingSpace = sectionInsets.left + sectionInsets.right
        let availableWidth = collectionView.frame.width - paddingSpace
        return CGSize(width: availableWidth, height: 120)
    }
    
    // tells the layout the spacing between the cells, headers, footers
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        insetForSectionAt section: Int) -> UIEdgeInsets {
        return sectionInsets
    }
    
    // controls the spacing between each line in the layout
    func collectionView(_ collectionView: UICollectionView,
                        layout collectionViewLayout: UICollectionViewLayout,
                        minimumLineSpacingForSectionAt section: Int) -> CGFloat {
        return sectionInsets.top
    }
}
