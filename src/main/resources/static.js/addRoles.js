function addNewUserFormOptions() {
    createForm.roles.options[0] = new Option('ADMIN', `1`);
    createForm.roles.options[1] = new Option('USER', `2`);
}

addNewUserFormOptions();