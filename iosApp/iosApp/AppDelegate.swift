//
//  AppDelegate.swift
//  iosApp
//
//  Created by user on 10.10.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SharedSDK
import UIKit


@UIApplicationMain
class AppDelegate: UIResponder, UIApplicationDelegate {
    
    var window: UIWindow?
    
    func application(_ application: UIApplication, didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?) -> Bool {
        
        let userAgent = "\(UIDevice.current.systemName) \(UIDevice.current.systemVersion)"
                let code = readFromInfoPlist(withKey: "CFBundleVersion") ?? "(unknown build number)"
                let version = readFromInfoPlist(withKey: "CFBundleShortVersionString") ?? "(unknown app version)"
        
                    PlatformSDK().doInit(
                        configuration: PlatformConfiguration(
                        userAgent: userAgent,
                        version: version,
                        code: code
                    )
                )
        
        window = UIWindow(frame: UIScreen.main.bounds)
        let homeViewController = Main_iosKt.mainController(window: window!)
        window!.rootViewController = homeViewController
        window!.makeKeyAndVisible()
        
        return true
    }
}

private let infoPlistDictionary = Bundle.main.infoDictionary

/// Retrieves and returns associated values (of Type String) from info.Plist of the app.
private func readFromInfoPlist(withKey key: String) -> String? {
    return infoPlistDictionary?[key] as? String
}
