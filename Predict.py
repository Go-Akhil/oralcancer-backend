import sys
import tensorflow as tf
import numpy as np
from PIL import Image

def preprocess_image(image_path):
    img = Image.open(image_path).convert("RGB").resize((256, 256))
    img_array = np.array(img) / 255.0
    return np.expand_dims(img_array, axis=0)  # shape: (1, 256, 256, 3)

def predict(image_path):
    model = tf.keras.models.load_model("MODEL_HSV.h5")
    image = preprocess_image(image_path)

    # Check expected input shapes
    input_shapes = [inp.shape for inp in model.inputs]

    # Prepare inputs based on shapes
    if len(input_shapes) != 2:
        raise ValueError("This model doesn't expect 2 inputs. Please verify the architecture.")

    input_1_shape = input_shapes[0]
    input_2_shape = input_shapes[1]

    # Determine which input is image and which is auxiliary
    if input_1_shape[1:] == (256, 256, 3):
        image_input = image
        dummy_input = np.zeros((1,) + tuple(input_2_shape[1:]))  # e.g., (1, 10)
        inputs = [image_input, dummy_input]
    elif input_2_shape[1:] == (256, 256, 3):
        image_input = image
        dummy_input = np.zeros((1,) + tuple(input_1_shape[1:]))  # e.g., (1, 10)
        inputs = [dummy_input, image_input]
    else:
        raise ValueError("No image input with shape (256, 256, 3) found in model inputs.")

    # Predict
    prediction = model.predict(inputs)
    print("Prediction probabilities:", prediction)

    if prediction.shape[1] == 1:  # Binary classification
        return "Cancer" if prediction[0][0] > 0.58 else "Benign"
    else:
        result = np.argmax(prediction)
        return "Cancer" if result == 1 else "Benign"

if __name__ == "__main__":
    image_path = sys.argv[1]
    label = predict(image_path)
    print("Predicted label:", label)





