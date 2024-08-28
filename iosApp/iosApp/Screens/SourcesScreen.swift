//
//  SourcesScreen.swift
//  iosApp
//
//  Created by Oleh Kholiavchuk on 29.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

extension SourcesScreen {
    
    @MainActor
    class SourcesViewModelWrapper: ObservableObject {
        let sourcesViewModel: SourcesViewModel
        
        init() {
            sourcesViewModel = SourcesInjector().sourcesViewModel
            sourcesState = sourcesViewModel.sourcesState.value
        }
        
        @Published var sourcesState: SourcesState
        
        func startObserving() {
            Task {
                for await sourcesS in sourcesViewModel.sourcesState {
                    self.sourcesState = sourcesS
                }
            }
        }
    }
}

struct SourcesScreen: View {
        
    @ObservedObject private(set) var viewModel: SourcesViewModelWrapper
    
    var body: some View {
        VStack {
            SourcesAppBar()
            
            if let error = viewModel.sourcesState.error {
                SourcesErrorMessage(message: error)
            }
            
            if(!viewModel.sourcesState.sources.isEmpty) {
                ScrollView {
                    LazyVStack(spacing: 10) {
                        ForEach(viewModel.sourcesState.sources, id: \.self) { source in
                            SourceImageView(source: source)
                        }
                    }
                }
            }
        }.onAppear {
            self.viewModel.startObserving()
        }
    }
}

struct SourcesAppBar: View {
    
    var body: some View {
        Text("Sources")
            .font(.largeTitle)
            .fontWeight(.bold)
    }
}

struct SourcesErrorMessage: View {
    var message: String
    
    var body: some View {
        Text(message)
            .font(.title)
    }
}

struct SourceImageView: View {
    var source: Source
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(source.name)
                .font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
                .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
            Text(source.desc)
            Text("\(source.country) - \(source.lang)")
                .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, alignment: .trailing)
                .foregroundStyle(.gray)        }
    }
}

#Preview {
    SourcesScreen(viewModel: .init())
}
