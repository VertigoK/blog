$('#btn-save').on('click', function() {
    saveUser();
});
$('#btn-update').on('click', function() {
    updateUser();
});
$('#btn-update-role').on('click', function() {
    updateRole();
});

function saveUser() {
    let data = {
        username: $('#username').val(),
        password: $('#password').val(),
        email: $('#email').val()
    };
    $.ajax({
        type: 'POST',
        url: '/auth/signup',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json',
    }).done(function(resp) {
        if(resp.statusCode === 200) {
            alert('회원 가입에 성공했습니다.');
            location.href = '/auth/loginForm';
        } else {
            alert('입력하신 Username은 이미 사용 중입니다. 다시 입력해 주세요.');
            location.href = '/auth/signupForm';
        }
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function updateUser() {
    let data = {
        id: $('#userId').val(),
        password: $('#password').val(),
        email: $('#email').val()
    };
    $.ajax({
        type: 'PUT',
        url: '/user/update',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
    }).done(function(resp) {
        alert('회원 정보가 수정되었습니다.');
        location.href = '/';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function updateRole() {
    let data = {
        id: $('#userId').val(),
        role: $('#newRole').val()
    };
    $.ajax({
        type: 'PUT',
        url: '/admin/updateRole',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
    }).done(function(resp) {
        alert('회원 권한이 수정되었습니다.');
        location.href = '/admin/userList';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function checkUsername() {
    $.ajax({
        type: 'POST',
        url: '/auth/checkUsernameRealTime',
        data: {
            username: $('#username').val()
        },
        success:function(resp) {
            if(resp == 1) {
                $('.username_already').css("display","inline-block");   //username 중복인 경우
                $('.username_ok').css("display", "none");
            } else {
                $('.username_ok').css("display","inline-block");    //username 중복이 아닌 경우
                $('.username_already').css("display", "none");
            }
        }
    });
}
