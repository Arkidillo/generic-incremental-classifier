# Import the required modules
from keras.preprocessing.image import img_to_array
from keras.preprocessing.image import ImageDataGenerator
from keras.utils import to_categorical
import glob
import os
import joblib
import numpy as np
import pickle
from config import *
import random
from keras.optimizers import Adam
from model import *

if __name__ == "__main__":

	ims_labels = []
	ims = []
	labels = []

	# instead of hog features - use pixel intensities
	# Load the positive features
	for pos_im in glob.glob(os.path.join(pos_im_path,"*")):
		print("Working on positive image:", pos_im)
		# Read image and convert to grayscale
		im = cv2.imread(pos_im)
		im = cv2.resize(im, scale_size)
		im = img_to_array(im)
		ims_labels.append([im, 1])

	# Load the negative features
	for neg_im in glob.glob(os.path.join(neg_im_path,"*")):
		im = cv2.imread(neg_im)
		im = cv2.resize(im, scale_size)
		im = img_to_array(im)
		ims_labels.append([im, 0])

	ims_labels = np.array(ims_labels)
	np.random.shuffle(ims_labels)

	# Un-tuple the images from their labels
	ims = ims_labels[:, 0]
	labels = ims_labels[:, 1]

	ims = ims / 255.0

	labels = to_categorical(labels, num_classes=2)
	ims_list = ims.tolist()
	labels_list = labels.tolist()



	# Create ImageDataGenerator
	aug = ImageDataGenerator(rotation_range=rotation_range, width_shift_range=width_shift_range,
							 height_shift_range=height_shift_range, shear_range=shear_range, zoom_range=zoom_range,
							 horizontal_flip=horizontal_flip, fill_mode=fill_mode)

	# Setup model
	net = Net.build(width=scale_size[0], height=scale_size[1], depth=3, classes=2)
	opt = Adam(lr=init_lr, decay=init_lr / epochs)
	net.compile(loss="binary_crossentropy", optimizer=opt)

	# Train
	# print("Training a Linear SVM Classifier")
	net.fit_generator(aug.flow(x=np.asarray(ims_list), y=labels, batch_size=bs), steps_per_epoch=len(ims) // bs, epochs=epochs)
	# net.fit(x=np.asarray(ims_list), y=labels,epochs=epochs)

	# If feature directories don't exist, create them
	if not os.path.isdir(model_path):
	    os.makedirs(model_path)


	# im = cv2.imread("mac.jpeg")
	# im = cv2.resize(im, scale_size)
	# im = img_to_array(im)
	# im = np.expand_dims(im, axis=0)
	#
	# print(net.predict(np.asarray(im.tolist())))
	#
	#
	# im = cv2.imread("eagle.jpg")
	# im = cv2.resize(im, scale_size)
	# im = img_to_array(im)
	# im = np.expand_dims(im, axis=0)
	#
	# print(net.predict(np.asarray(im.tolist())))

	# Save model
	net.save("model.net")
