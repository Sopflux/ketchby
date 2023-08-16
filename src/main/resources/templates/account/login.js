
		Kakao.init("7c248b2a7ecace1d70680b17adec6fb8");
		function kakaologin() {
			Kakao.Auth.loginForm({
				scope: "profile_nickname,account_email",
				success: function () {
					Kakao.API.request({
						url: "/v2/user/me",
						success: function (res) {
							var name = res.kakao_account.profile.nickname;
							var email = res.kakao_account.email;
							console.log(name);
							console.log(email);
							$.ajax({
								url: '/kakaologin',
								method: 'post',
								data: {name: name, email: email},
								success:function(){
								//location.href = "/kakaologin/" + name + "/" + email;
								location.href = "/mypage2";
									
								}
							})
						}
					})
				}
			})
		}
		$(function () {
			$("#openID").click(function (event) {
				event.preventDefault();
				$("#findID").modal("show");

			});
			$("#openPwd").click(function (event) {
				event.preventDefault();
				$("#findPwd").modal("show");

			});
			var email;
			var auth = 0;
			$("#btn_idcheck").click(function () {
				id = $("#id_pwd").val();
				console.log("id :", id);
				$.ajax({
					url: '/idCheck',
					method: 'GET',
					data: {id: id},
					success: function (data) {
						if (data === "") {
							alert("존재하지 않는 아이디입니다.")
						} else if (data != id) {
							alert("아이디가 일치하지 않습니다.")
						} else {
							id = data
							auth += 1;
						}
					},
					error: function (error) {
						// 오류 처리
						console.error('Error:', error);
					}
				});

			})

			$("#btn_emailcheck").click(function () {
				email = $("#email").val()
				console.log(email);
				$.ajax({
					url: '/duplicateEmail',
					data: {email: email},
					success: function (email2) {
						console.log("email:", email2)
						if (email2 === "") {
							alert("아이디와 일치하지않는 이메일 입니다!")
						} else {
							$("#box_check").css("display", "block")
							console.log("email check 동작함!")
							$.ajax({
								url: '/emailCheck',
								method: 'GET',
								data: {email: email},
								success: function (data) {
									authNum = data
								},
								error: function (error) {
									// 오류 처리
									console.error('Error:', error);
								}
							}); // end email Check ajax
						}

					}
				})

			}); // end btn_check email
			$("#btn_emailcheck2").click(function () {
				email = $("#email2").val()
				console.log(email);
				var id_pwd = $("#id_pwd").val();
				console.log(id);
				$.ajax({
					url: '/emailCheckWithEmail',
					data: {email: email},
					success: function (email2) {
						console.log("email:", email2)
						if (email2 === "") {
							alert("존재하지 않는 이메일 입니다!")
						} else {
							$("#box_check").css("display", "block")
							console.log("email check 동작함!")
							$.ajax({
								url: '/emailCheck',
								method: 'GET',
								data: {email: email, id: id_pwd},
								success: function (data) {
									authNum = data
								},
								error: function (error) {
									// 오류 처리
									console.error('Error:', error);
								}
							}); // end email Check ajax
						}

					}
				})

			}); // end btn_check email
			$(".btn_close").click(function(){
				$("#email").val("");
			})
			$("#btnCheckNUM").click(function (event) {
				var userNum = $("#checkNUM").val()
				if (authNum == userNum) {
					alert("인증되었습니다!");
					auth += 1;
					event.preventDefault();
					$.ajax({
						url: '/getID',
						method: 'GET',
						data: {email: email},
						success: function (id) {
							$("#id").val(id);
						},
						error: function (error) {
							// 오류 처리
							console.error('Error:', error);
						}
					});
				} else {
					alert("다시 인증해주세요!")
					auth = 0;
					event.preventDefault();
				}
			});
			$("#btnCheckNUM2").click(function (event) {
				var userNum = $("#checkNUM2").val()
				if (authNum == userNum) {
					alert("인증되었습니다!");
					auth += 1;
					event.preventDefault();
					$.ajax({
						url: '/getID',
						method: 'GET',
						data: {email: email},
						success: function (id) {
							$("#id").val(id);
						},
						error: function (error) {
							// 오류 처리
							console.error('Error:', error);
						}
					});
				} else {
					alert("다시 인증해주세요!")
					auth = 0;
					event.preventDefault();
				}
			});

			if (auth == 2) {
				$("#changePwd").css("display", "block");
			}
		})
