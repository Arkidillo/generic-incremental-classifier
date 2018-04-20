# Import the required modules
from skimage.feature import local_binary_pattern
from sklearn.svm import LinearSVC
from sklearn.linear_model import LogisticRegression
import glob
import os
import joblib
import numpy as np
from sklearn import svm
import pickle

if __name__ == "__main__":
    pos_feat_dir = 'positive_features/'
    neg_feat_dir = 'negative_features/'

    model_path = 'linear_svc_model/'

    fds = []
    labels = []
    # Load the positive features
    for feat_path in glob.glob(os.path.join(pos_feat_dir,"*.feat")):
        print("Working on positive image:", feat_path)
        fd = joblib.load(feat_path)
        fds.append(fd)
        labels.append(1)

    # Load the negative features
    for feat_path in glob.glob(os.path.join(neg_feat_dir,"*.feat")):
        fd = joblib.load(feat_path)
        fds.append(fd)
        labels.append(0)

    fds_arr = np.asarray(fds)
    labels_arr = np.asarray(labels)

    clf = svm.SVC(gamma = 0.001, C=100)
    print("Training a Linear SVM Classifier")
    clf.fit(fds_arr, labels_arr)
    # If feature directories don't exist, create them
    if not os.path.isdir(model_path):
        os.makedirs(model_path)

    model_path = os.path.join(model_path, 'trained_svm.clf')
    pickle.dump(clf, open(model_path, 'wb'))
    print("Classifier saved to {}".format(model_path))
