import streamlit as st
from jamaibase import JamAI, protocol as p
from docx import Document
from io import BytesIO
import random
import string
from PyPDF2 import PdfReader
from tempfile import NamedTemporaryFile
from PIL import Image
import base64
import streamlit.components.v1 as components
import os

# Initialize JamAI
jamai = JamAI(
    api_key="jamai_sk_5165f07d5b5af13d393057db34944dfea901b8b088a7e3fc",
    project_id="proj_9262d3bb8414d5ccde38a36e"
)

def extract_text_from_pdf(pdf_file):
    pdf = PdfReader(pdf_file)
    text = ""
    for page in pdf.pages:
        page_text = page.extract_text()
        if page_text:
            text += page_text + "\n"
    return text

def generate_random_filename(extension=".docx"):
    random_str = ''.join(random.choices(string.ascii_letters + string.digits, k=10))
    return f"final_report_{random_str}{extension}"

# Streamlit App Config
st.set_page_config(page_title="CulturaMyLens.ai", page_icon="📝")
st.title("🌟 Malaysia's Tiger Eye - Understand Malaysia Like A Native")

# Custom CSS Styling
st.markdown(
    """
    <style>
    body {
        background-color: #1e1e1e;
        color: #f0f0f0;
    }
    .generated-output {
        background-color: #444;
        padding: 15px;
        border-radius: 10px;
        margin-top: 20px;
        box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.5);
        color: #f0f0f0;
    }
    .generated-output h4 {
        color: #FFA500;
    }
    .stButton>button {
        background-color: #4CAF50;
        color: white;
        border: none;
        border-radius: 5px;
        padding: 10px 20px;
        cursor: pointer;
    }
    .stButton>button:hover {
        background-color: #45a049;
    }
    </style>
    """,
    unsafe_allow_html=True
)

# StoryTeller UI
with st.container():
    st.header("📖 Malaysian Cultural Storyteller")
    st.write("Enter the name of a Malaysian building or place to hear its cultural story.")

    place_input = st.text_input("🏛️ Name of the building or place (e.g., A Famosa, Kek Lok Si, Kampung Baru)")

    if st.button("📚 Generate Story", use_container_width=True):
        if place_input.strip():
            try:
                story_completion = jamai.add_table_rows(
                    "action",
                    p.RowAddRequest(
                        table_id="StoryTeller",  # Case-sensitive; make sure it's exactly the table name
                        data=[{"object": place_input}],
                        stream=False
                    )
                )

                if story_completion.rows:
                    row = story_completion.rows[0].columns
                    story_output = row.get("story")  # 🔧 FIXED: Correct column name in your table

                    if story_output and story_output.text.strip():
                        formatted_text = story_output.text.replace('\n', '<br>')

                        st.subheader("🏛️ Cultural Story")
                        st.markdown(
                            f"""
                            <div class="generated-output">
                                <p>{formatted_text}</p>
                            </div>
                            """,
                            unsafe_allow_html=True
                        )
                    else:
                        st.warning("⚠️ No story was generated.")
                else:
                    st.warning("⚠️ The system returned no rows.")
            except Exception as e:
                st.error(f"❌ Error generating story: {e}")
        else:
            st.warning("⚠️ Please enter a building or place name.")


# 🍲 Super Ultimate Secret Recipe UI
with st.container():
    st.header("🍽️ Super Ultimate Secret Recipe")
    st.write("Upload a photo of any Malaysian food to uncover its name and secret recipe!")

    uploaded_file = st.file_uploader("📸 Upload your Malaysian food photo", type=["jpg", "jpeg", "png"])
    analyze_button = st.button("🍛 Reveal Recipe", use_container_width=True)

    if uploaded_file and analyze_button:
        st.write("Filename:", uploaded_file.name)

        # Convert uploaded image to JPEG and save temporarily
        with NamedTemporaryFile(delete=False, suffix=".jpeg") as temp_file:
            try:
                image = Image.open(uploaded_file).convert("RGB")
                image.save(temp_file, format="JPEG")
                temp_file_path = temp_file.name
            except Exception as e:
                st.error(f"❌ Image processing error: {e}")
                st.stop()

        # Upload image to JamAIBase
        try:
            upload_response = jamai.file.upload_file(temp_file_path)
        except Exception as e:
            st.error(f"❌ Failed to upload image: {e}")
            st.stop()

        # Clean up temp file
        os.remove(temp_file_path)

        # Send image URI to FoodRecipe table
        try:
            completion = jamai.table.add_table_rows(
                "action",
                p.RowAddRequest(
                    table_id="FoodRecipe",
                    data=[{"Food": upload_response.uri}],
                    stream=False,
                ),
            )
        except Exception as e:
            st.error(f"❌ Recipe generation failed: {e}")
            st.stop()

        # Preview uploaded image
        image_bytes = uploaded_file.getvalue()
        encoded_image = base64.b64encode(image_bytes).decode()
        st.image(f"data:image/jpeg;base64,{encoded_image}", width=300, caption="Uploaded Food Image")

        # Display recipe if available
        if completion.rows:
            row = completion.rows[0].columns
            recipe_output = row.get("Recipe")

            if recipe_output and recipe_output.text.strip():
                formatted_text = recipe_output.text.replace('\n', '<br>')
                st.subheader("👨‍🍳 Your Secret Recipe")
                st.markdown(
                    f"""
                    <div class="generated-output">
                        <p>{formatted_text}</p>
                    </div>
                    """,
                    unsafe_allow_html=True
                )
            else:
                st.warning("⚠️ No recipe was returned. Try another image.")
        else:
            st.warning("⚠️ The system returned no rows. Try again later.")
