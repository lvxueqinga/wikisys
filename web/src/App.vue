<template>
    <a-layout>
        <a-layout-header class="header">
            <div class="logo" />
            <a-menu
                    v-model:selectedKeys="selectedKeys1"
                    theme="dark"
                    mode="horizontal"
                    :style="{ lineHeight: '64px' }"
            >
                <a-menu-item key="1">首页</a-menu-item>
                <a-menu-item key="2">电子书管理</a-menu-item>
                <a-menu-item key="3">关于我们</a-menu-item>
            </a-menu>
        </a-layout-header>
        <router-view/>




        <a-layout-footer style="text-align: center">
            wiki ©2024 Created by Tailai
        </a-layout-footer>
    </a-layout>

</template>


<style>
    #components-layout-demo-top-side-2 .logo {
        float: left;
        width: 120px;
        height: 31px;
        margin: 16px 24px 16px 0;
        background: rgba(255, 255, 255, 0.3);
    }

    .ant-row-rtl #components-layout-demo-top-side-2 .logo {
        float: right;
        margin: 16px 0 16px 24px;
    }

    .site-layout-background {
        background: #fff;
    }
</style>
