JobbyBobby.ai – The Complete AI-Powered Recruitment Suite

JobbyBobby.ai is a two-part intelligent recruitment system:

- Jobby – a JavaFX-based desktop application that lets HR configure company/job info, upload CVs, and analyze candidates using DeepSeek AI
- Bobby – a Streamlit-based web platform offering premium features like candidate ranking, interview generation, MBTI analysis, and face reading

Together, they help HR professionals hire smarter, faster, and more fairly.

Jobby (Java Desktop App)

Built With:
- Java 17+
- JavaFX / FXML / SceneBuilder
- DeepSeek API (LLM)
- Apache PDFBox
- JamAIBase API (optional resume storage)
- Maven for dependency management

Features:
- Configure company info and job descriptions
- Set weightage on skill, education, and background (100% total)
- Upload CV (PDF) and analyze it
- Get:
  - Candidate summary
  - Suggested role
  - Skill breakdown
  - Estimated salary range
  - Job match score (0–100)

Bobby (Streamlit Web App)

Premium AI Tools:
- TalentRank: Upload 2–5 CVs and auto-select the best-fit candidate
- Interview Expert: Generate technical, behavioral, and role-specific questions
- MBTI Analyzer: Paste essay → get MBTI result + personality breakdown
- Fortune Face Reader: Upload face photo → analyze traits and role fit (e.g., media/TV)

Built With:
- Python
- Streamlit
- JamAIBase API
- PyPDF2, Pillow, python-docx, base64, tempfile

How to Run

▶️ Bobby (Streamlit Web App)

Bobby has been deployed to Streamlit Cloud and can be accessed at:

🔗 **[https://bobbyjobby.streamlit.app](https://bobbyjobby.streamlit.app)**

> You do not need to run the web app locally. It is triggered directly via a button in the Jobby desktop interface.

▶️ Jobby (Java Desktop App)

Clone repo and install dependencies
git clone https://github.com/Mungchochak/JOBBY.git

