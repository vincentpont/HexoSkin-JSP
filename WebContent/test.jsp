<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Demo: Getting an email address using the Google+ Sign-in button</title>
  <style type="text/css">
  .hide { display: none;}
  .show { display: block;}
  </style>
  
  <script src="https://apis.google.com/js/plusone.js" type="text/javascript"></script>
  <script src="https://apis.google.com/js/client:plusone.js" type="text/javascript"></script>
  <script type="text/javascript">
  /*
   * D�clench� lorsque l'utilisateur accepte la connexion, annule ou ferme la
   * bo�te de dialogue d'autorisation.
   */
  function loginFinishedCallback(authResult) {
    if (authResult) {
      if (authResult['error'] == undefined){
        gapi.auth.setToken(authResult); // Stocker le jeton renvoy�.
        toggleElement('signin-button'); // Masquer le bouton de connexion lorsque l'ouverture de session r�ussit.
        getEmail();                     // D�clencher une requ�te pour obtenir l'adresse e-mail.
      } else {
        console.log('An error occurred');
      }
    } else {
      console.log('Empty authResult');  // Un probl�me s'est produit
    }
  }

  /*
   * Initie la requ�te au point de terminaison userinfo pour obtenir l'adresse
   * e-mail de l'utilisateur. Cette fonction d�pend de gapi.auth.setToken, qui doit contenir un
   * jeton d'acc�s OAuth valide.
   *
   * Une fois la requ�te achev�e, le rappel getEmailCallback est d�clench� et re�oit
   * le r�sultat de la requ�te.
   */
  function getEmail(){
    // Charger les biblioth�ques OAuth2 pour activer les m�thodes userinfo.
    gapi.client.load('oauth2', 'v2', function() {
          var request = gapi.client.oauth2.userinfo.get();
          request.execute(getEmailCallback);
        });
  }

  function getEmailCallback(obj){
    var el = document.getElementById('email');
    var email = '';

    if (obj['email']) {
      email = 'Email: ' + obj['email'];
    }

    console.log(obj);   // Retirer les commentaires pour inspecter l'objet complet.

    el.innerHTML = email;
    toggleElement('email');
  }

  function toggleElement(id) {
    var el = document.getElementById(id);
    if (el.getAttribute('class') == 'hide') {
      el.setAttribute('class', 'show');
    } else {
      el.setAttribute('class', 'hide');
    }
  }
  </script>
</head>
<body>
  <div id="signin-button" class="show">
     <div class="g-signin" data-callback="loginFinishedCallback"
      data-approvalprompt="force"
      data-clientid="799362622292-cisd7bgllvoo1pckcsm38smvl9ec1m60.apps.googleusercontent.com"
      data-scope="https://www.googleapis.com/auth/plus.login https://www.googleapis.com/auth/userinfo.email"
      data-height="short"
      data-cookiepolicy="single_host_origin"
      >
    </div>
  </div>

  <div id="email" class="hide"></div>
</body>
</html>

