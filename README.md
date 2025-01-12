# URL Shortener
This project is a simple URL shortener implemented in Java. It allows users to input 
a long URL and receive a shortened version, which can be used to redirect back to 
the original URL.

## Features
 - **Shorten URLs**: Convert long URLs into shorter, more manageable links.
 - **Redirect**: Accessing the shortened URL redirects the user to the original long URL.

   ## Getting Started
   ### Prerequisites
   - Java Development Kit (JDK) 8 or higher
   ### Installation
   1. **Clone the repository**:
      ```bash
      git clone https://github.com/Ahmedelshinnawi/Java-URL-Shortener.git
      ```
   2. **Navigate to the project directory**:
      ```bash
      cd Java-URL-Shortener
      ```
   3. **Navigate to src directory**:
      ```bash
      cd src/
   4. **Compile the project**:
      ```bash
      javac URLShortener.java 
      ```

## Running the Application
1. **Start the server**
   ``` bash
   java URLShortener
2. **Access the application**:
   Open your web browser and navigate to ``http://localhost:8080``.

## Usage
 - **Shorten a URL**: Enter a long URL into the input field and click "Shorten". The
   application will generate a shortened URL.
 - **Redirection**: Accessing the shortened URL will redirect you to the original long URL.

## Project Structure
  

    
    Java-URL-Shortener
    ├──  src/
    |    └── URLShortener.java
    ├── public/
    |   ├── index.html
    |   ├── shortener.html
    |   └── 404.html                  
    ├── .gitignore
    └── README.md

  - **src**: Contains the Java source files.
  - **public**: Contains the Static HTML files for the web interfac.

 ## Contributing
 Contributions are welcome! Please fork this repository and submit a pull request 
 for any enhancements or bug fixes.

 ## Acknowledgments
 Inspired by various URL shortener implementations and tutorials.
