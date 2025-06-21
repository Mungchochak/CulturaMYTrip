CulturaMyTrip.ai – The Complete AI-Powered Cultural Experience Suite
CulturaMyTrip.ai is a two-part intelligent cultural discovery system designed to preserve, promote, and enhance engagement with Malaysia’s heritage:

• CulturaMyTrip – a JavaFX-based desktop application that helps users input personal interests, travel goals, and image data to explore Malaysian cultural locations and experiences using DeepSeek AI
• MyCulturaLens – a Streamlit-based web platform offering smart features like food recognition, recipe generation, and cultural storytelling using image and text inputs
Together, they help tourists, locals, and educators experience Malaysia more deeply — through stories, places, and traditional cuisine.
 
CulturaMyTrip (Java Desktop App)

🧱 Built With:
•	Java 22
•	JavaFX / FXML / SceneBuilder
•	DeepSeek API (LLM)
•	Apache PDFBox
•	JamAIBase API (optional cultural data storage)
•	Maven for dependency management
🌟 Features:
•	Upload or describe cultural interests (e.g., nature, temples, food)
•	Input travel duration, budget, region, or departure point
•	Analyze and get:
o	Suggested locations
o	Story-based recommendations
o	Custom cultural routes (e.g., 3-day heritage trail)
o	Estimated trip cost and schedule
 
MyCulturaLens (Streamlit Web App)
🔍 Premium AI Features:
•	Cultural Storyteller: Input a place name → get an engaging historical and cultural narrative
•	Super Ultimate Secret Recipe: Upload a Malaysian food photo → get its name and recipe
•	Tourist Lens (coming soon): Snap a photo → get detailed cultural context of buildings, crafts, or street scenes
🧰 Built With:
•	Python
•	Streamlit
•	JamAIBase API
•	Pillow (PIL), PyPDF2, python-docx, base64, tempfile
📂 Code for MyCulturaLens is under the folder: MyCulturaLens in this repository
📁 JamAIBase Action Table examples: https://drive.google.com/drive/folders/1OG1UnPgKVYiIlXYmKLsZyj73nOqwMNpf?usp=sharing
 
🚀 How to Run

▶️ CulturaMyTrip (Java Desktop App)
1.	Clone the repository and install dependencies: https://github.com/Mungchochak/CulturaMYTrip.git
2.	Import into IntelliJ IDEA or other Java IDE
3.	Run Launcher.java in src/
4.	JavaFX UI will allow image input + cultural trip generation

How Geocoding Works (Google Maps Integration)

After generating a travel plan using CulturaMyTrip, users will see **Famous Attractions** as clickable location suggestions.  
When a user clicks on any of these locations:

✅ The system automatically opens Google Maps in the browser  
✅ It uses geocoding (based on the location name or coordinates) to provide real-time directions  
✅ This helps tourists seamlessly transition from virtual recommendations to **real-world navigation**

🔗 Example: Clicking on “Kampung Morten” will open its exact location in Google Maps for further exploration.

▶️ MyCulturaLens (Streamlit Web App)
MyCulturaLens is deployed and ready to use here:

🔗 https://myculturalens.streamlit.app/

 (link placeholder — replace with actual CulturaMyLens deployment)
You don’t need to run it locally. It's triggered directly from the CulturaMyTrip desktop app.
 

This makes the app not just informative but **actionable** — turning suggestions into real cultural experiences.

 
🧭 Why It Matters
Created for the Visit Malaysia 2026 Hackathon, this suite bridges tradition and technology. By using AI to tell stories and suggest experiences, we hope to inspire deeper appreciation of Malaysia’s living heritage — one photo and one journey at a time.

