/**
 * All User Related Validation Methods. 
 */

// Methods For Update A User Validations.
function validateUpdation() {
	 if (!userDropDownValidationForUpdation()) {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: hidden;'; 
	 }
}

function userDropDownValidationForUpdation() {
	return !(document.userUpdationContainer1.userDropDownValuesForUpdation.value == -1);
}

// Methods For Delete A User Validation. 
function validateDeletion() {
	 if (!userDropDownValidationForDeletion()) {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: visible;';
		 event.preventDefault();
	 } else {
		 document.getElementById("userDropDownValidationCheck").style = 'visibility: hidden;'; 
	 }
}

function userDropDownValidationForDeletion() {
	return !(document.userDeletionContainer.userDropDownValuesForDeletion.value == -1);
}