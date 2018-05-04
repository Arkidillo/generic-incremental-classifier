# Directories

import os

# set to true if running from .sh
running_from_sh = True

# path relative to the .sh file
this_dir = 'src/svm_classifier/'

pos_im_path = 'positive_images/'
neg_im_path = 'negative_images/'

pos_feat_dir = 'positive_features/'
neg_feat_dir = 'negative_features/'

model_path = 'linear_svc_model/'
model_file = 'linear_svc_model/trained_svm.clf'

labels_csv = 'labels.csv'

# Hog parameters
orientations = 9
pixels_per_cell = (4, 4)
cells_per_block = (3, 3)

# NMS
threshold = 0.1

# Test classifier
test_im_dir = 'test_images/'
step_size = (10, 10)
downscale = 1.25
visualize_det = False
show_final_det = True

# appends the
if (running_from_sh):
	pos_feat_dir = this_dir + pos_feat_dir
	neg_feat_dir = this_dir + neg_feat_dir
	model_path = this_dir + model_path
	model_file = this_dir + model_file
	labels_csv = this_dir + labels_csv
else:
	pos_im_path = os.path.join('..', os.path.join('..', pos_im_path))
	neg_im_path = os.path.join('..', os.path.join('..', neg_im_path))
	test_im_dir = os.path.join('..', os.path.join('..', test_im_dir))

import glob
import os
import cv2

# Get the minimum size of the positive images
def get_sliding_window_sz():
	# List of the dimensions for each image
	x_dim = []
	y_dim = []
	print(pos_im_path)
	for im_path in glob.glob(os.path.join(pos_im_path, "*")):
		print(im_path)

		# Read image and convert to grayscale
		im = cv2.imread(im_path)
		x_dim.append(im.shape[1])
		y_dim.append(im.shape[0])


		# Scale the y to the nearest
		x = min(x_dim)
		y = int(((min(y_dim)/pixels_per_cell[0])) * pixels_per_cell[0])
	return (x, y)
