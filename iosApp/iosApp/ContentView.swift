import SwiftUI
import shared

/// `BadViewable` also conforms to `Flow<Unit>`, which actually the source of the bug
class BadView: BadBaseView, BadViewable {
    
    lazy var contentView = ContentView(tapHandler: { [weak self] in
        self?.emitEvent()
    })
}

// This one works fine and doesn't have the bug
class GoodView: BaseGoodView, GoodViewable {
    
    lazy var contentView = ContentView(tapHandler: { [weak self] in
        self?.emitEvent()
    })
}

struct ContentView: View {
    
    private let tapHandler: () -> ()
    
    init(tapHandler: @escaping () -> ()) {
        self.tapHandler = tapHandler
    }
    
    var body: some View {
        Button(action: tapHandler, label: {
            Text("Tappity tap")
        })
    }
}
