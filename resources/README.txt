Assignment 7 changes to the model:

    The model was changed by restructuring our motions, as keyframes. The keyframes only stored
    the ending state of any motion, and our model would tween the animation between two consecutive
    keyframes.

    Our keyframes are structured as a doubly linked list. Each key frame has knowledge of
    the previous keyframe and the next keyframe. This type of format also makes editing key
    frames very straight forward.

    Our model has added functionality to insert, delete, or edit a keyframe for a given shape.
    Depending on the operation the model will either modify existing keyframes, or insert and delete
    a keyframe into the animation. With the doubly linked list format, the model can set the
    previous and next nodes accordingly and can easily insert and delete keyframes without
    disrupting the structure of the animation.

    The ability to create and delete shapes was also added to the model. This functionality built
    upon older methods that create shapes when the animation description is first passed to the
    model, and the deletion of the elements was done by removing the specific element from
    all the data structures that store it.

Assignment 7 Key Notes:

    In order to fulfill the design specification, a new enhanced visual view was created that
    implements an IView. This enhanced view offers the functionality to start, pause, restart,
    loop, edit, and save an animation.

    All of these actions are done through a GUI using java swing, and there is no console keyboard
    commands required. The user can interactively click on the buttons to edit an animation and then
    has the option to create or delete a shape and to edit, insert, or delete keyframes for a
    specific shape. The user can also save an edited animation as a text or svg file.

    In order to move the management of the timer and other utility items out of the model and view
    components of the design, a controller was created to handle the interaction between the view
    and running the model. Due to the way our GUI was designed the view did have to interact with
    the model to pass user input from text fields to the model, however, all the playback controls
    were handled through the controller.

    A new JPanel was created to house all the view functionality of the enhanced view. The Editor
    panel manages the display of the animation, as well as the mouse handling and button management
    of the user.

Operational Notes:

    The way our model is structured, each tick represents a frame, so the speed is both the
    frame rate and the speed of execution. For optimal animation rendering use speeds above 10.

    ***Important***
    Sometimes the panel backgrounds do not overlay properly. I.e. the edit panel might show up over
    the animation panel, where you can still see the animation in the background underneath,
    this is just a graphical problem, however. Everything still operates and works as expected.

    Please select a shape from the scroll panel to delete or edit a key frame.

    And please select a key frame from the options before clicking the edit or delete buttons.
