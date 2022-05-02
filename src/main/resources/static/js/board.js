$(document).ready(function() {
    $('#content').summernote({
        tabsize: 2,
        height: 300
    });
});

$('#btn-save').on('click', function() {
    saveBoard();
});
$('#btn-delete').on('click', function() {
    deleteBoard();
});
$('#btn-update').on('click', function() {
    updateBoard();
});
$('#btn-save-reply').on('click', function() {
    saveReply();
});

function saveBoard() {
    let data = {
        title: $('#title').val(),
        content: $('#content').val()
    };
    $.ajax({
        type: 'POST',
        url: '/board/write',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
    }).done(function(resp) {
        alert('글이 작성되었습니다.');
        location.href = '/';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function updateBoard() {
    let id = $('#boardId').val();
    let data = {
        title: $('#title').val(),
        content: $('#content').val()
    };
    $.ajax({
        type: 'PUT',
        url: '/board/update/' + id,
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
    }).done(function(resp) {
        alert('글이 수정되었습니다.');
        location.href = '/';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function deleteBoard() {
    let id = $('#boardId').text();
    $.ajax({
        type: 'DELETE',
        url: '/board/delete/' + id,
        dataType: 'json'
    }).done(function(resp) {
        alert('글이 삭제되었습니다.');
        location.href = '/';
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function saveReply() {
    let id = $('#board-id').val();
    let data = {
        content: $('#reply-content').val()
    };
    $.ajax({
        type: 'POST',
        url: '/board/' + id + '/reply',
        data: JSON.stringify(data),
        contentType: 'application/json; charset=utf-8',
        dataType: 'json'
    }).done(function(resp) {
        alert('댓글이 작성되었습니다.');
        location.href = '/board/' + id;
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}

function deleteReply(boardId, replyId) {
    $.ajax({
        type: 'DELETE',
        url: '/board/' + boardId + '/reply/' + replyId,
        dataType: 'json'
    }).done(function(resp) {
        alert('댓글이 삭제되었습니다.');
        location.href = '/board/' + boardId;
    }).fail(function(error) {
        alert(JSON.stringify(error));
    });
}