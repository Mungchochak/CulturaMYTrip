package org.example.CulturaMYTrip.Model;

public class DeepSeekcustomizingPromptModel {

    public String getSkillMatchingprompt(String additionalInfo) {

        return "Please analyze whether this resume matches our company and the designated job position based on the following provided details. "
                + "Use the company and position information only if they appear meaningful and relevant. "
                + "If the information is clearly incorrect, irrelevant, or disorganized, reply: The company information or job information is not clear "
                + "If there are no relevant hard skills found in the resume related to the company or position, reply: The interviewees do not match "
                + "Otherwise, extract matching technical skills.\n\n"
                + "This is the company information and the designated job position:\n"
                + additionalInfo
                + "\n--------------------------------------\n"
                + "You are a professional resume analysis system. If the resume is in a language other than English, first translate it to English. "
                + "You are an expert in analyzing all academic and professional fields. Please extract and summarize this person's core hard skills, technologies, methods, and tools based on their resume. "
                + "Hard skills include any field-specific technical abilities such as laboratory methods, engineering tools, diagnostic procedures, software systems, programming languages, modeling platforms, medical techniques, design tools, or statistical packages. "
                + "Do NOT include soft skills or behavioral traits like teamwork, leadership, communication, creativity, or adaptability. "
                + "Respond with only hard skill keywords separated by ' | ' (e.g., Java | SPSS | AutoCAD | PCR | Adobe Illustrator), in a single formal English line, strictly under 220 characters (including spaces and symbols). "
                + "Do not include any explanations or analysis. "
                + "There is no need to tell me the total number of characters. This is for the users to see, not for the background. "
                + "If the content is not a resume, please reply: No corresponding skills checked "
                + "Your reply must be in English.";
    }

    public String getWorkingExperienceprompt() {



        return "You are a professional resume analysis system. Extract the candidate’s major **project or responsibility-based experiences**, including:\n"
                + "- Personal projects\n"
                + "- Group or coursework projects\n"
                + "- Group projects or capstone projects\n"
                + "- Internship-based or job-based projects\n"
                + "- Significant responsibilities or initiatives in formal employment\n\n"
                + "Use this structured format exactly for each experience:\n"
                + "Category: [e.g., Work Project, Internship Project, Coursework Project]\n"
                + "→ Role: [e.g., Developer, Lead, Researcher, Coordinator]\n"
                + "→ Organization: [Company, School, or Platform involved]\n"
                + "→ Year: [e.g., 2023 or 2020.06 - 2021.09]\n"
                + "→ Description: [Key tasks, technologies, objectives, and impact]\n\n"
                + "Each entry must follow this format and be separated by a blank line.\n"
                + "If no relevant content is found, return: \"insufficient data\".";

    }

    public String getPersonalityprompt(String additionalInfo) {

        return "You are a professional resume analysis system. If the resume is in a language other than English, translate it to English first. "
                + "You are also given optional company and job position context. If the provided information is relevant and meaningful, include in your analysis whether the candidate’s personality traits are suitable for the company's culture or the specified job. "
                + "If the information is invalid, unclear, or irrelevant, ignore it and proceed with standard analysis.\n\n"
                + "Company & Position Info:\n" + additionalInfo + "\n\n"
                + "Analyze and summarize the candidate’s personality traits based on descriptions of their roles, responsibilities, and projects. "
                + "Focus on traits such as leadership, creativity, problem-solving, collaboration, attention to detail, etc. "
                + "Respond with a single formal English paragraph under 260 characters (including spaces and symbols). "
                + "Do not include assumptions or system comments. "
                + "If insufficient data is found, return: insufficient data. "
                + "If the document is not a resume, reply: The document is not related to the resume.";
    }


    public String getSoftSkillsprompt() {

        return "You are a professional resume analysis system. If the resume is not in English, translate it first. " +
                "Then extract:\n" +
                "1. All language proficiencies mentioned in the resume.\n" +
                "2. All soft skills such as communication, leadership, teamwork, adaptability, etc.\n\n" +
                "Respond in the following format (only this format):\n\n" +
                "Language: English | Mandarin | Spanish\n" +
                "Soft Skills: Leadership | Problem-solving | Teamwork\n\n" +
                "Do NOT include any explanations, levels (e.g. fluent), or commentary. Return 'insufficient data' if nothing is found in each category respectively.\n" +
                "Use only English in your response.";
    }

    public String getPositionprompt(String additionalInfo) {
        return "You are a professional resume analysis system. Translate the resume to English if necessary.\n\n"
                + "You are also provided with company and specific job position information below:\n"
                + "Company & Job Context:\n" + additionalInfo + "\n\n"
                + "Your task is to recommend **exactly 5 job positions** based on the resume content.\n\n"
                + "⚠️ Important Rules:\n"
                + "1. The **first recommendation must always** be the company-provided job title, regardless of candidate fit.\n"
                + "   - If the candidate does not fit this job, still include it with a match score of 0% and a justification.\n"
                + "2. The remaining 4 recommendations should be positions that are more suitable based on the resume's content.\n"
                + "3. Do not skip any entries. Always return a total of **5 positions**.\n\n"
                + "Use the **exact format below** for all entries:\n\n"
                + "Category: [e.g., Software Engineering, UX Design, Data Analysis]\n"
                + "→ Position: [Job Title, e.g., Front-End Developer]\n"
                + "→ Match: [xx% or 0%]\n"
                + "→ Description: [Why this position fits (or doesn't fit) the candidate. Reference resume content like experience, tools, or skills. Mention relevance to company/job if applicable.]\n\n"
                + "Ensure all roles are backed by actual resume data. Keep the response clean, structured, and in formal English.\n\n"
                + "If the resume has no valid content to evaluate, respond exactly with: The document is not related to the resume.";
    }




    public String getInformationprompt() {

        return "You are a professional resume analysis system. Extract the candidate’s basic personal and academic background using the format below:\n\n"
                + "Name: [Full name in English or Pinyin]\n"
                + "Location: [City or region]\n"
                + "Major: [University major, translated if needed]\n"
                + "Certificates:\n"
                + "- [Concise Certificate Name 1]: [A clear and specific explanation of what the certificate represents, including the issuing body, proficiency level, and purpose]\n"
                + "- [Concise Certificate Name 2]: [Detailed explanation as above]\n\n"
                + "Each field must start on a new line. Certificate names must be short and human-readable (e.g., 'CET4', 'HSK 5'), followed by a colon and a complete English explanation.\n"
                + "Do not include generic phrases like 'a certificate' or 'proves proficiency' without specifying in what and how.\n"
                + "If any section is not available, omit it. Do not include any markdown, bullet formatting, or commentary.\n"
                + "Your response must be in clean, well-formatted English only.";

    }



    public String getSalaryprompt(String additionalInfo) {
        return "You are a professional resume analysis system. Based on the candidate’s resume and the company & position information provided below, estimate a suitable monthly salary range in Malaysian Ringgit (RM).\n\n"
                + "Company & Position Context:\n" + additionalInfo + "\n\n"
                + "The salary must be predicted according to the local talent market conditions of the company's address. If the company address is missing or unclear, use Malaysian national averages and industry standards as the basis.\n\n"
                + "If the company data (e.g., salary or title) is missing or marked as 'N/A', provide your best estimate based solely on the resume and state that company data was incomplete.\n\n"
                + "Strict format:\n"
                + "Recommended Salary: RMxxxx–xxxx[* if above or below company range]\n"
                + "Explanation: [Why this range is recommended based on resume content. If * is used, explain why it’s outside the range.]\n\n"
                + "Examples:\n"
                + "Recommended Salary: RM1800–2300\n"
                + "Explanation: Based on internship experience and moderate Java skills aligned with part-time expectations.\n\n"
                + "OR\n"
                + "Recommended Salary: RM2800–3500*\n"
                + "Explanation: Strong full-stack project work and AWS knowledge justify a higher-than-expected part-time range.\n\n"
                + "If resume has no valid content to assess, respond exactly with: \"insufficient data\"\n"
                + "Only return clean, professional English. Never omit the 'Recommended Salary' line.";
    }


    public String getScoreprompt(String additionalInfo) {

        return "You are a professional resume evaluation system. You will be provided with a candidate's resume along with the company's job and salary requirements.\n\n"
                + "Company & Position Context:\n" + additionalInfo + "\n\n"
                + "If the provided context is clear and relevant, incorporate it into your evaluation. Evaluate not just the general strength of the resume, but how well it fits the company's industry, culture, and job expectations. "
                + "If the context appears irrelevant or invalid, proceed with standard evaluation only.\n\n"
                + "Evaluate the resume and assign a score out of 100. The score must reflect the candidate's:\n"
                + "- Work experience\n"
                + "- Project quality\n"
                + "- Academic background\n"
                + "- Technical or research skills\n"
                + "- Relevance to the provided job/industry (if applicable)\n"
                + "- Overall resume clarity and structure\n\n"
                + "Strictly respond in this format:\n\n"
                + "xx/100\n\n"
                + "Explanation:\n"
                + "1. Evaluation of work experience: diversity, seniority, and relevance to the provided position if applicable\n\n"
                + "2. Assessment of project quality: complexity, outcomes, technologies, and match with the target job if applicable\n\n"
                + "3. Academic background: strength, institution ranking, field alignment with company domain\n\n"
                + "4. Technical/research skills: tools, platforms, frameworks, and relation to company requirements if provided\n\n"
                + "5. Resume structure: organization, clarity, impact, and formatting\n\n"
                + "Final Evaluation:\n"
                + "Evaluate how well the resume aligns with the company's expectations and scoring weights. State whether the score fairly reflects the candidate's fit for the role based on the provided company and job data.\n\n"
                + "You must always include the following at the end of your response:\n\n"
                + "----------------\n"
                + "Scoring Weights Configuration:\n"
                + "Skill Match: xx%\n Educational background: xx%\n Work experience: xx%\n\n"
                + "Scoring Weights is based on what I gave you"
                + "All responses must be in professional English. Do not include markdown, comments, or unstructured formatting.";
    }

    public String getNameprompt() {

        return "You are a professional resume parsing system. From the content of the resume, extract only the candidate’s full name. "
                + "The name is typically found at the top of the resume, or near the contact information. "
                + "Ignore all labels like 'Name:', 'Resume', or file names. "
                + "If the resume is in a language other than English, translate the name to Pinyin or standard English spelling if possible. "
                + "Only return the name. Do not include any other information, explanations, titles, greetings, or extra words. "
                + "If the content does not resemble a resume or contains no identifiable name, reply exactly with: \"name not found\". "
                +"translate the name to Pinyin or standard English spelling if possible"
                + "Your response must be in English.";

    }

    public String getGraduatedprompt() {

        return "You are a professional resume analysis system. From the resume, extract the name of the highest-level educational institution attended (e.g., university, college, or high school).\n"
                + "Return only the institution name in this format:\n"
                + "- If the name ends with 'University', remove it and add 'Uni'. (e.g., Stanford University → Stanford Uni)\n"
                + "- If it ends with 'College', leave it unchanged or use its abbreviation.\n"
                + "- If it's neither, return the name directly.\n\n"
                + "Then provide detailed explanation in this format, one line per item:\n"
                + "Explanation:\n"
                + "1. School background and academic strengths\n"
                + "2. World ranking (QS, THE, etc.)\n"
                + "3. Ranking for the candidate's field of study\n"
                + "4. Notable alumni or public figures\n\n"
                + "If no education-related information is found, return exactly: \"insufficient data\".\n"
                + "Respond in English. Do not use markdown or additional formatting.";
    }

    public String getCgpaprompt() {
        return "You are a professional resume parser. From the resume, extract the candidate’s CGPA (Cumulative Grade Point Average) or GPA if available.\n\n"
                + "Return the GPA strictly in the format:\n"
                + "x.x/4.0 or xx/4.0 (e.g., 3.4/4.0 or 3.75/4.0)\n\n"
                + "If multiple GPAs are listed, return the highest.\n"
                + "Do not include any explanation, prefix, label, or extra text — only return the GPA value.\n"
                + "If there is no GPA or CGPA mentioned at all, reply exactly with: \"insufficient data\".\n"
                + "Respond only in English.";
    }

}
