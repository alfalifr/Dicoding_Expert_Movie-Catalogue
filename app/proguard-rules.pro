##---------------Begin: proguard configuration for SQLCipher  ----------
#noinspection ShrinkerUnresolvedReference
-keep,includedescriptorclasses class net.sqlcipher.** { *; }
#noinspection ShrinkerUnresolvedReference
-keep,includedescriptorclasses interface net.sqlcipher.** { *; }