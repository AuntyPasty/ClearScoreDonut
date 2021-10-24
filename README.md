ClearScore donut app

This app is a simple test app for ClearScore. It is written in Kotlin with Rx and Dagger.

The basic function is to display a circle representing the score and the score value within the circle.

The app uses the retrofit library with the Gson converter factory to convert the response from a known endpoint to a data object. 

There is a custom view to draw and display the circle by drawing onto Canvas.

The code also contains some simple unit tests and espresso UI tests