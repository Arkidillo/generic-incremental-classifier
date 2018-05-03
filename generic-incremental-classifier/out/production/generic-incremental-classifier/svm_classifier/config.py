# Directories
pos_im_path = 'positive_images/'
neg_im_path = 'negative_images/'

pos_feat_dir = 'positive_features/'
neg_feat_dir = 'negative_features/'

model_path = 'linear_svc_model/'
model_file = 'linear_svc_model/trained_svm.clf'

# Hog parameters
orientations = 9
pixels_per_cell = (4, 4)
cells_per_block = (3, 3)

# NMS
threshold = 0.1

# Test classifier
test_image = 'test-1.pgm'
step_size = (10, 10)
downscale = 1.25
visualize_det = True


import glob
import os
import cv2

# Get the minimum size of the positive images
def get_sliding_window_sz():
	# List of the dimensions for each image
	x_dim = []
	y_dim = []
	for im_path in glob.glob(os.path.join(pos_im_path, "*")):

		# Read image and convert to grayscale
		im = cv2.imread(im_path)
		x_dim.append(im.shape[1])
		y_dim.append(im.shape[0])


		# Scale the y to the nearest
		x = min(x_dim)
		y = int(((min(y_dim)/pixels_per_cell[0])) * pixels_per_cell[0])
	return (x, y)