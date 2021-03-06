# Weather Forecast Application

This is  an android app, which can retrieve weather information based on their searching criteria and render the searched results on dashboard screen


## Software development priciple
- S.O.L.I.D, DRY (Don't Repeat Yourself)

## Design pattern & practice

- Seperate model using in different layer: database entity, api response, domain layer, presentation layer
- Seperate component and provide dependency injection for each component
- App Architecture: Clean architecture

## Folder structure

- `data`: Contain database, api service, and repository. This provide data for above layer
- `domain`: Contain usecases, entity and interface of repository. This execute business logic.
- `presentation`: Contain UI component: activity, fragment, viewmodel ... This layer execute usecases and render result to UI 
- `extension`: This contain kotlin extension function
- `utils`: This contain utility component such as GSonUtils, transformer ...

## Apply Android library and framework

- Android architecture component: ViewModel, Lifecycle, LiveData, ViewBinding, Room
- Android Navigation component
- Network: Retrofit
- Async program: Kotlin coroutine and flow
- Dependency injection: Dagger 2
- Data converting: Gson

## Require steps in order to  run application

- Clone project from repository: `git clone https://github.com/kenguyen224/weatherforecast.git`
- Open project by android studio (4.x.x).
- Build and install app into android phone or virtual machine.

## Checklist
- [x] The application is a simple Android application which is written by Java/Kotlin.
- [x] The application is able to retrieve the weather information from OpenWeatherMapsAPI.
- [x] The application is able to allow user to input the searching term.
- [x] The application is able to proceed searching with a condition of the search term length must be from 3 characters or above.
- [x] The application is able to render the searched results as a list of weather items.
- [x] The application is able to support caching mechanism so as to prevent the app from generating a bunch of API requests.
- [x] The application is able to manage caching mechanism & lifecycle.
- [x] The application is able to handle failures.
- [x] The application is able to support the disability to scale large text for who can't see the text clearly.
- [ ] The application is able to support the disability to read out the text using VoiceOver controls.  
- [x] Programming language: Kotlin.
- [x] Design app's architecture: MVVM
- [x] Apply LiveData mechanism
- [x] UI should be looks like in attachment.
- [x] WriteUnitTests
- [x] Exceptionhandling
- [x] Cachinghandling
- [ ] SecureAndroidappfrom:
	+ [ ] DecompileAPK
	+ [ ] Rooteddevice
	+ [x] Data transmission via network
	+ [ ] Encryption for sensitive information
- [ ] Accessibility for Disability Supports:
	+ [ ] Talkback: Use a screen reader.
	+ [x] Scaling Text: Display size and font size: To change the size of items on your screen, adjust the display size or font size.
- [ ] Entity relationship diagram for the database and solution diagrams for the components, infrastructure design if any 
- [x] Readme file includes:
	+ [x] Brief explanation for the software development principles, patterns & practices being applied
	+ [x] Brief explanation for the code folder structure and the key Java/Kotlin libraries and frameworks being used
	+ [x] All the required steps in order to get the application run on local computer
	+ [x] Checklistofitemsthecandidatehasdone.

## Need to improve

- Handle save  ViewState in ViewModel

## About me
[Kenv](https://www.linkedin.com/in/nguyenvanke96/)

Email: kenguyen224@gmail.com
