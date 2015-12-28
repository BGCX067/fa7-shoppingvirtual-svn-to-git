
/* efeito do topo */
$(document).ready(function(){

	$(".btSetaIcones").click(function(){
	  $(".icone").slideToggle("slow");
	  $(this).toggleClass("active");
	});

});

/* efeito do datagrid "tabela" */
$(document).ready(function(){

	$("h2.tit").click(function(){
	  $(".encolherTabela").slideToggle("slow");
	  $(this).toggleClass("setaTitulo");
	});

});


// ---------- Script Abas ---------- 
$(function() {
	// oculta todas as abas
	$("div.contentAba").hide();

	// mostra somente  a primeira aba
	$("div.contentAba:first").show();
	
	// seta a primeira aba como selecionada (na lista de abas)
	$("#abas a:first").addClass("selected");
	
	// quando clicar no link de uma aba
	$("#abas a").click(function(){
	
		// oculta todas as abas
		$("div.contentAba").hide();
		
		// tira a sele��o da aba atual
		$("#abas a").removeClass("selected");
		
		// adiciona a classe selected na selecionada atualmente
		$(this).addClass("selected");
		
		// mostra a aba clicada
		$($(this).attr("href")).show();
		
		// pra nao ir para o link
		return false;
	
	});
	
});



/* --------------------------- Menu dod Topo ----------------------------- */

// Initialization, you can leave this here or move this somewhere else
$(function(){
	$('ul.jd_menu').jdMenu({	onShow: loadMenu,
								//onHideCheck: onHideCheckMenu,
								//onHide: onHideMenu, 
								//onClick: onClickMenu, 
								onAnimate: onAnimate
								});
	$('ul.jd_menu_vertical').jdMenu({onShow: loadMenu, onHide: unloadMenu, offset: 1, onAnimate: onAnimate});
});

function onAnimate(show) {
	//$(this).fadeIn('slow').show();
	if (show) {
		$(this)
			.css('visibility', 'hidden').show()
				.css('width', $(this).innerWidth())
			.hide().css('visibility', 'visible')
		.fadeIn('normal');
	} else {
		$(this).fadeOut('fast');
	}
}

var MENU_COUNTER = 1;
function loadMenu() {
	if (this.id == 'dynamicMenu') {
		$('> ul > li', this).remove();

		var ul = $('<ul></ul>');
		var t = MENU_COUNTER + 10;
		for (; MENU_COUNTER < t; MENU_COUNTER++) {
			$('> ul', this).append('<li>Item ' + MENU_COUNTER + '</li>');
		}
	}
}

function unloadMenu() {
	if (MENU_COUNTER >= 30) {
		MENU_COUNTER = 1;
	}
}

// We're passed a UL
function onHideCheckMenu() {
	return !$(this).parent().is('.LOCKED');
}

// We're passed a LI
function onClickMenu() {
	$(this).toggleClass('LOCKED');
	return true;
}






