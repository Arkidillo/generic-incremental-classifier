import cv2

import glob
import os
import joblib
from skimage.feature import hog
from config import *

scale_size = get_sliding_window_sz()
print(scale_size)

# If feature directories don't exist, create them
if not os.path.isdir(pos_feat_dir):
    os.makedirs(pos_feat_dir)

# If feature directories don't exist, create them
if not os.path.isdir(neg_feat_dir):
    os.makedirs(neg_feat_dir)

print("Calculating the descriptors for the positive samples and saving them")
for im_path in glob.glob(os.path.join(pos_im_path, "*")):
	print("Working on file: ", im_path)

	# Read image and convert to grayscale
	im = cv2.imread(im_path)
	gray_im = cv2.cvtColor(im, cv2.COLOR_RGB2GRAY)

	resize_im = cv2.resize(gray_im, scale_size)
	# Resize the image
	fd = hog(resize_im, orientations=orientations, pixels_per_cell=pixels_per_cell, 
		cells_per_block=cells_per_block, visualise=False)

	# Save features
	fd_name = os.path.split(im_path)[1].split(".")[0] + ".feat"
	fd_path = os.path.join(pos_feat_dir, fd_name)
	joblib.dump(fd, fd_path)
print("Positive features saved in {}".format(pos_feat_dir))

print("Calculating the descriptors for the negative samples and saving them")
for im_path in glob.glob(os.path.join(neg_im_path, "*")):
	print("Working on file: ", im_path)

	im = cv2.imread(im_path)
	gray_im = cv2.cvtColor(im, cv2.COLOR_RGB2GRAY)

	resize_im = cv2.resize(gray_im, scale_size)

	fd = hog(resize_im, orientations=orientations, pixels_per_cell=pixels_per_cell, 
		cells_per_block=cells_per_block, visualise=False)

	fd_name = os.path.split(im_path)[1].split(".")[0] + ".feat"
	fd_path = os.path.join(neg_feat_dir, fd_name)
	joblib.dump(fd, fd_path)
print("Negative features saved in {}".format(neg_feat_dir))

print("Completed calculating features from training images")
