# generic-incremental-classifier
A classifier that can be trained incrementally by a user to classify anything, based on what the user decides to label.

## Install
1. Get python dependencies: `pip install -r generic-incremental-classifier/src/smv_classifier/requirements.txt`
2. Install JDK 8

## Run
1. Place all images in `generic-incremental-classifier/images/`
1. `cd generic-incremental-classifier/`
2. Run `obj-classifier.sh`

3. This will open a labeling software, where you should draw boxes around each object you want to detect.
4. Click Next to iterate through this batch of photos
5. Click Done -> Close the window

6. An object detector will be trained based on your labeling
7. The detector will make guesses on a new batch of images
8. The labeling software will reopen, where the detector's guesses will be displayed
9. Delete/ make new boxes so the images is correctly labeled

10. (This 6->9 will be repeated, such that the model should get better and better at guessing)
11. This will output the trained keras model to model.net
