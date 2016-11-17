/**
 * Jake Mitchell (jmitch32), Koushul Ramjattun (kramjatt)
 * Golf_Mitchell_Ramjattun
 * 16 November 2016
 *
 * TAs: Jiageng Zhang, Dallis Polashenski
 */


=======================
// Project Structure //
=======================

1. Simulator - The Simulator object is the master file containing the app itself. This file generates the window and
                all of its dependencies. Run the project by running this file.

2. Canvas - The panel in which all of the fireworks are rendered.

3. FireworksContainer - The panel in which all of the firework controller GUIs are generated for user input.

4. FireworkController - A self-contained file that both renders a GUI to retrieve user input and saves the info in a
                         way that can be accessed later. Creates Firework objects and Guideline objects.

5. Firework - An object that receives all of the info from a FireworkController and renders a firework on the canvas.

6. Guideline - Another object that receives input from the FireworkController, but this one receives the updates live
                and updates the canvas to show the user a guideline for the firework to be generated.


======================
// Flow Of The Code //
======================

The app starts with the Simulator class. This class creates a JFrame and displays it. Then it adds a Canvas and a
FireworksContainer to it. The Canvas just sits there waiting, while the FireworkContainer proceeds to create GUI
elements. First, it generates some controls to manage the FireworkControllers (like "launch all" and "add new").
Then, it adds a container object with a scrollbar to contain the FireworkController GUIs (try adding 5+ fireworks!).

From there, the GUI waits for you to add a firework. When you do, a new FireworkController is created. This file
then generates all of the GUI items necessary for the firework. Then it creates a Guideline object that is used to give
the user visual feedback for the effect of their GUI tweaks. Then it adds the Guideline to the Canvas' array of
Guidelines. This is because every time the Canvas is redrawn, it redraws ALL Guidelines AND Fireworks. This is so that
nothing is erased between launches. Note: "Launch All" erases the canvas before iteratively launching every Firework.

When the user clicks "Launch" on a FireworkController, a new Firework object is created and added to the Canvas' array
of Fireworks. If they delete it, it will be removed from the FireworksContainer's list of FireworkControllers.

==================
// Extra Credit //
==================

For this project, we did two big things for extra credit:

1. Dynamic GUI.
    - We added the FireworksContainer for the sole purpose of allowing the user to remove/add new fireworks as they
    please. Theoretically, an unlimited number of fireworks can be created and independently controlled, launched,
    removed, and even all launched together via the GUI.

2. Guidelines.
    - The biggest problem we saw with the project was that it was hard to visualize what each slider actually modified
    in realtime. The user had to trial-error until they got what they wanted. So instead, we added the Guideline
    object. This object receives information from a FireworkController and there is exactly one Guideline for each
    firework. The Guideline is updated on the Canvas every time any slider value is changed. This allows the user
    to accurately assume how the firework will behave when launched in realtime.


