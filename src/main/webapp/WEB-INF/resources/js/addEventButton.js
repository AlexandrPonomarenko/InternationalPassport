$(function () {
    window.setupResponseBlockCustomer = (function () {
        const getPageURL = () => {
            return $(location).attr('href');
        };

        let controlData = {
            queryData: {
                login: null,
                statusAccount: null
            },
            url: getPageURL(),
            headerToken: $("meta[name='_csrf']").attr('content'),
            headerName: $("meta[name='_csrf.header']").attr('content')
        };

        const setupData = (e) => {
            const parent = $(e.target).parent();
            let attrSpanLogin =$(parent).find('span[data-login]').attr('data-login');
            let attrSpanStatus =$(parent).find('span[data-status]').attr('data-status');
            controlData.queryData.login = attrSpanLogin;
            controlData.queryData.statusAccount = attrSpanStatus;
            if ($(e.target).text() === 'Block') {
                $(e.target).text('Unblock');

            } else {
                $(e.target).text('Block');
            }
            // console.log('attrSpanLogin attr ', attrSpanLogin);
            // console.log('attrSpanStatus attr ', attrSpanStatus);
            // console.log('meta -- ', document.querySelectorAll('meta[name]'));
            // console.log('meta -- ', $("meta[name='_csrf']"));
            // console.log('SHOW ME MY QUERY YO!', ajaxCustomQuery);
        };
        const addEventClick = () => {
            $('.customer-data').find('button[data-block]').each((i, item) => {
                $(item).on('click',  (e) => {
                    setupData(e);
                    ajaxCustomQuery(controlData);
                });
            })
        };

        const setupResponseData = (objectResponse) => {
            const spanLogin = $('span[data-login]');
            // console.log('spanLogin', spanLogin);
            spanLogin.each((i, item) => {
                let attrLogin = $(item).attr('data-login');
                // console.log(attrLogin, 'FIND ITEM _ outside-  ', item);
                if (attrLogin === objectResponse.login) {
                    // console.log(attrLogin, 'FIND ITEM _ -  ', $(item).parent());
                    let parent = $(item).parent();
                    const spanStatus = parent.find('span[data-status]');
                    // console.log('paren -- ', parent.find('span[data-status]'));
                    spanStatus.attr('data-status', objectResponse.statusAccount);
                    spanStatus.text(objectResponse.statusAccount);
                }
            })

        };

        addEventClick();
        return setupResponseData;
    })();
});