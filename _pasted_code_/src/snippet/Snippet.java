package snippet;

public class Snippet {
	{
	  "version": 2,
	  "builds": [
	    {
	      "src": "ELMSPROJECT/Dockerfile", 
	      "use": "@vercel/docker"
	    }
	  ],
	  "routes": [
	    {
	      "src": "/(.*)",
	      "dest": "/app.war"  
	    }
	  ]
	}
}
