import shared
import UIKit
import SwiftUI

class SceneDelegate: UIResponder, UIWindowSceneDelegate {

    var window: UIWindow?

    var interactor: Interactor!

    func scene(_ scene: UIScene, willConnectTo session: UISceneSession, options connectionOptions: UIScene.ConnectionOptions) {
        guard let windowScene = scene as? UIWindowScene else { return }

        //let contentView = setUpGood()
        let contentView = setUpBad()
        
        let window = UIWindow(windowScene: windowScene)
        window.rootViewController = UIHostingController(rootView: contentView)
        interactor.attach()
        self.window = window
        window.makeKeyAndVisible()
    }
    
    // example 1 -- this works as expected
    private func setUpGood() -> ContentView {
        let goodView = GoodView()
        interactor = Interactor(goodView: goodView, badView: nil)
        return goodView.contentView
    }

    // example 2 -- this causes strange Flow behavior
    private func setUpBad() -> ContentView {
        let badView = BadView()
        interactor = Interactor(goodView: nil, badView: badView)
        return badView.contentView
    }
}

