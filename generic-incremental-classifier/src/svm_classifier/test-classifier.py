# Import the required modules
from keras.preprocessing.image import img_to_array
from skimage.transform.pyramids import pyramid_gaussian
from keras.models import load_model
import cv2
from nms import nms
import pickle
from config import *
import pandas as pd
import numpy as np

def sliding_window(image, window_size, step_size):
    '''
    This function returns a patch of the input image `image` of size equal
    to `window_size`. The first image returned top-left co-ordinates (0, 0)
    and are increment in both x and y directions by the `step_size` supplied.
    So, the input parameters are -
    * `image` - Input Image
    * `window_size` - Size of Sliding Window
    * `step_size` - Incremented Size of Window
    The function returns a tuple -
    (x, y, im_window)
    where
    * x is the top-left x co-ordinate
    * y is the top-left y co-ordinate
    * im_window is the sliding window image
    '''
    for y in range(0, image.shape[0], step_size[1]):
        for x in range(0, image.shape[1], step_size[0]):
            yield (x, y, image[y:y + window_size[1], x:x + window_size[0]])

if __name__ == "__main__":

    min_wdw_sz = scale_size
    rects = []

    # Iterate through test_images folder
    for im_path in glob.glob(os.path.join(test_im_dir, "*")):
        print("Testing classifier on file: ", im_path)

        im = cv2.imread(im_path)
        original_image = im.copy()

        # Load the classifier
        print(model_file)
        net = load_model("model.net")

        # List to store the detections
        detections = []
        # The current scale of the image
        scale = 0
        # Downscale the image and iterate
        for i in range(0, 6):
            # This list contains detections at the current scale
            cd = []
            # If the width or height of the scaled image is less than
            # the width or height of the window, then end the iterations.
            if im.shape[0] < min_wdw_sz[1] or im.shape[1] < min_wdw_sz[0]:
                break
            for (x, y, im_window) in sliding_window(im, min_wdw_sz, step_size):
                if im_window.shape[0] != min_wdw_sz[1] or im_window.shape[1] != min_wdw_sz[0]:
                    continue
                # # Calculate the HOG features
                # fd = hog(im_window, orientations=orientations, pixels_per_cell=pixels_per_cell,
                #     cells_per_block=cells_per_block, visualise=False)
                # fd = fd.reshape(1, -1)

                im_arr = img_to_array(im_window)
                im_arr = np.expand_dims(im_arr, axis=0)
                im_arr = np.asarray(im_arr.tolist())

                # print(net.predict(im_arr))
                pred = net.predict_proba(im_arr)[0,1]
                if pred == 1.0:
                # if pred == 1:
                    print("Detection:: Location -> ({}, {})".format(x, y))
                    print("Scale ->  {} | Confidence Score {} \n".format(scale, pred))
                    detections.append((x, y, 1,
                        int(min_wdw_sz[0]*(downscale**scale)),
                        int(min_wdw_sz[1]*(downscale**scale))))
                    cd.append(detections[-1])
                # If visualize is set to true, display the working
                # of the sliding window
                if visualize_det:
                    clone = im.copy()
                    for x1, y1, _, _  in cd:
                        # Draw the detections at this scale
                        cv2.rectangle(clone, (x1, y1), (x1 + im_window.shape[1], y1 +
                            im_window.shape[0]), (0, 0, 0), thickness=2)
                    cv2.rectangle(clone, (x, y), (x + im_window.shape[1], y +
                        im_window.shape[0]), (255, 255, 255), thickness=2)
                    cv2.imshow("Sliding Window in Progress", clone)
                    cv2.waitKey(30)
            # Move the the next scale
            scale+=1
            im = cv2.pyrDown(im)

        # Display the results before performing NMS
        clone = original_image.copy()
        for (x_tl, y_tl, _, w, h) in detections:
            # Draw the detections
            cv2.rectangle(im, (x_tl, y_tl), (x_tl+w, y_tl+h), (0, 0, 0), thickness=2)
        # cv2.imshow("Raw Detections before NMS", im)
        # cv2.waitKey()

        # Perform Non Maxima Suppression
        detections = nms(detections, threshold)

        # Display the results after performing NMS

        for (x_tl, y_tl, _, w, h) in detections:
            # Draw the detections
            #clone = cv2.resize(clone, (512, 512))
            cv2.rectangle(clone, (x_tl, y_tl), (x_tl+w,y_tl+h), (0, 0, 0), thickness=2)
            # Output rectangle tuple (x, y, width, height) to json
            if (running_from_sh):
            	rects.append((x_tl, y_tl, w, h, im_path[len(test_im_dir):]))
            else:
            	rects.append((x_tl, y_tl, w, h, im_path[18:]))

        cv2.imshow("Final Detections after applying NMS", clone)
        if show_final_det:
            cv2.waitKey()

    df = pd.DataFrame(rects)
    df.to_csv(labels_csv, header=False, index=False)
