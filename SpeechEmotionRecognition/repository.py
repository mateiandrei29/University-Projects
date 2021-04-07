import os

import librosa

import numpy as np


def load_data():
    happy = []
    sad = []
    angry = []
    fear = []

    L = []
    # Traversing through data
    for root, dirs, files in os.walk('data'):
        L.append(dirs)
    foldere = L[0]
    # print(foldere)
    for i in foldere:
        # print(i)
        for root, dirs, files in os.walk('data/' + i):
            # print(files)
            for j in files:

                x, sr = librosa.load('data/' + i + '/' + j)
                mfccs = librosa.feature.mfcc(x, sr=sr)
                # print(flatten(mfccs))

                if i == 'angry':
                    angry.append(list(np.ndarray.flatten(mfccs))[:1000])
                elif i == 'sad':
                    sad.append(list(np.ndarray.flatten(mfccs))[:1000])
                elif i == 'fear':
                    fear.append(list(np.ndarray.flatten(mfccs))[:1000])
                elif i == 'happy':
                    happy.append(list(np.ndarray.flatten(mfccs))[:1000])
    # print(happy)
    return angry, fear, happy, sad
