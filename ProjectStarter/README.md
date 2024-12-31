# My Personal Project - Family Tree
My project for this term is to create a family tree software that allows you to enter information about your family members and show how they are related. The GUI I create will be used to maneuver/modify a visual family tree so that it is easily accessible and simple for anyone using the software.

The software will provide the following features:
- Add family members and information
- Remove Family Member from family tree.
- Compare two family members ages.
- See what the oldest living family member is.
- **Possibly** Establish relationships between family members **(e.g. bon is john's cousin)**
- **Possibly** Have the ability to establish events between family members that had occurred **(e.g. a hiking trip that person A did with person B)**
- **Possibly** having all the data accumulate into a visual timeline of events.

This software intended to be used by anyone interested in developing/tracking their family tree. The reason I am creating this project is because I've taken up the interest in my family tree after recently visiting my family. After searching the internet, I was not able to find a software which contained all the features that I wanted. Building this would be an asset to me and other people who share the passion of recording their family's history.

## User Stories:
- As a User, I want to be able to add a family member to a family tree and specify their firstName, lastName, birth year, death year, alive, and provide a description of their life.
- As a User, I want to be able to view a list of all the family members in a family tree.
- As a User, I want to be able to delete a family member from a family tree.
- As a User, I want to be able to compare the age of two family members.
- As a User, I want to be able to find the which member lived the longest and how long they lived for.
- As a User, I want to be able to calculate the mean age that dead family members lived to (lifespan).
- As a User, I want to be able to load a family tree from a file.
- As a User, I want to be given the option to load data when my program starts.
- As a User, I want to be able to save my family tree data to a file.
- As a User, I want to be given the option to save data before quitting the program.


These require more complex structures than Phase 1, potential ideas for future phases:
- As a User, I want to be able to view a list of all the relationships a family has to a particular family member **(e.g. Joe is: Bob's son, Johannes’s son, Chris’s uncle, etc.).** This will be connected to the UI aspect allowing us to visually see who is related to who.

# Instructions for End User

- You can "adding multiple Xs to a Y" by click on the "Add Member" button located on the left panel and proceed to fill out the form and click submit when you are done.
- You can view your tree by clicking "View tree" on the left panel. Green family member names signify alive and red family member names signify deceased.

- You can generate the first required action related to the user story "adding multiple Xs to a Y" (first add members as mentioned above) by clicking "View Tree" on the left panel and clicking the "Remove" button next to the family member you would like to remove. This will remove a member from the tree.
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by (first add members as mentioned above) by clicking "View Tree" on the left panel and clicking the "Oldest Member" button. This will create a popup with the oldest family member in the tree.
- You can generate the third (optional) action related to the user story "adding multiple Xs to a Y" by (first add members as mentioned above) by clicking "View Tree" on the left panel and clicking the "Lifespan" button. This will create a popup with the average lifespan of deceased family members.
- You can locate my visual component by booting up the program to the default page, it is located in the center of the screen as my app logo.
- You can save the state of my application by clicking the button on the left panel "Save" button. This results in a popup with the status of the save.
- You can reload the state of my application by clicking the "Load" button either when starting up on the default page in the center screen or clicking the "Load" button located on the left side of the screen at any time. This results with a popup with the status of the load and you get navigated to the "View Tree" display to see the newly loaded data. 

# Phase 4: Task 2

Mon Nov 25 16:50:24 PST 2024 Added family Member: Timmy Doe to family tree

Mon Nov 25 16:50:40 PST 2024 Added family Member: George Doe to family tree

Mon Nov 25 16:50:58 PST 2024 Added family Member: Jane Doe to family tree

Mon Nov 25 16:54:08 PST 2024 Added family Member: Smith Doe to family tree

Mon Nov 25 16:54:14 PST 2024 Calculated oldest family member age 77

Mon Nov 25 16:54:15 PST 2024 Calculated lifespan of 64 years for family tree

Mon Nov 25 16:54:19 PST 2024 Removed family Member: Jane Doe from family tree

Mon Nov 25 16:54:20 PST 2024 Calculated oldest family member age 51

Mon Nov 25 16:54:22 PST 2024 Calculated lifespan of 51 years for family tree

Mon Nov 25 16:54:23 PST 2024 Removed family Member: George Doe from family tree

Mon Nov 25 16:54:24 PST 2024 Calculated oldest family member age 34

Mon Nov 25 16:54:26 PST 2024 Calculated lifespan of 0 years for family tree

Mon Nov 25 16:54:29 PST 2024 Removed family Member: Timmy Doe from family tree

Mon Nov 25 16:54:30 PST 2024 Removed family Member: Smith Doe from family tree

# Phase 4: Task 3

When reflecting on my project, I would probably refactor the project by adding an abstract class called "UserInterface". I would've used the abstract class for both the FamilyTreeUI and FamilyTreeGUI. This would've saved me time developing the FamilyTreeGUI because I developed similar features in the FamilyTreeUI (load, save, etc). Ultimately an abstract class like this would allow me to have an easier time producing future scalable projects involving user interfaces as most of the guidelines for the code required for any new UIs would be contained primarly within the abstract class. I believe this is a good usage of abstract classes as in this example it can be used to reduce code repetition.