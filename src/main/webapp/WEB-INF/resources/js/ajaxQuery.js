$(() => {
    window.ajaxCustomQuery = (() => {
        const query = (objectParam) => {
            $.ajax({
                url: '/InternationalPassport/control',
                contentType : "application/json",
                type: 'POST',
                beforeSend: (xmlHttpRequest) => {
                    console.log('beforeSend ', objectParam);
                    console.log('beforeSend xmlHttpRequest', xmlHttpRequest);
                    xmlHttpRequest.setRequestHeader(objectParam.headerName, objectParam.headerToken);
                },
                success: (data) => {
                    console.log('GET RESPONCE FROM SERVER !', data);
                    // console.log('setupResponseBlockCustomer -- ', setupResponseBlockCustomer);
                    setupResponseBlockCustomer(data);
                },
                error: () => {
                    console.log('WAS EROOR');
                },
                data: JSON.stringify(objectParam.queryData)

                // data: 'login=' + objectParam.queryData.login + '&status=' + objectParam.queryData.status
            });
        };
        return query;
    })();
});